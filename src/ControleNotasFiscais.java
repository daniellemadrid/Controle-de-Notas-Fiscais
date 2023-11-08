import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;

public class ControleNotasFiscais {
    private BufferedReader leitor;

    public ControleNotasFiscais() {
        try {
            leitor = new BufferedReader(new FileReader("src/notas_fiscais_00100.csv"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executa() throws IOException {

        String linha;
        ListaNotaFiscal listaNf = new ListaNotaFiscal();
        try {
            leitor.readLine();
            Scanner sc = new Scanner(leitor.readLine()).useDelimiter("[|]");

            String numero = sc.next();
            Date data = Date.valueOf(sc.next());
            String cliente = sc.next();
            String cpf = sc.next();
            String end = sc.next();
            String cidade = sc.next();
            String estado = sc.next();

            NotaFiscal nf = new NotaFiscal();
            nf.setNumero(numero);
            nf.setData(data);
            nf.setCliente(cliente);
            nf.setCnpjCpf(cpf);
            nf.setEndereco(end);
            nf.setCidade(cidade);
            nf.setEstado(estado);
            listaNf.adicionar(nf);

            String notaAtual = numero;
            String notaAnterior = notaAtual;

            ListaItemNotaFiscal lista = new ListaItemNotaFiscal();
            nf.setItens(lista);

            String numeroItem = sc.next();
            String descricao = sc.next();
            int quantidadeItem = sc.nextInt();
            double valorUnitario = sc.nextDouble();
            ItemNotaFiscal item = new ItemNotaFiscal(numeroItem, descricao, quantidadeItem, valorUnitario);
            lista.adicionar(item);

            sc.close();
            while ((linha = leitor.readLine()) != null) {
                sc = new Scanner(linha).useDelimiter("[|]");
                numero = sc.next();
                data = Date.valueOf(sc.next());
                cliente = sc.next();
                cpf = sc.next();
                end = sc.next();
                cidade = sc.next();
                estado = sc.next();
                notaAtual = numero;
                if (!notaAnterior.equals(notaAtual)) {
                    nf = new NotaFiscal();
                    nf.setNumero(numero);
                    nf.setData(data);
                    nf.setCliente(cliente);
                    nf.setCnpjCpf(cpf);
                    nf.setEndereco(end);
                    nf.setCidade(cidade);
                    nf.setEstado(estado);
                    lista = new ListaItemNotaFiscal();
                    nf.setItens(lista);
                    listaNf.adicionar(nf);
                    notaAnterior = notaAtual;
                }
                numeroItem = sc.next();
                descricao = sc.next();
                quantidadeItem = sc.nextInt();
                valorUnitario = sc.nextDouble();
                item = new ItemNotaFiscal(numeroItem, descricao, quantidadeItem, valorUnitario);
                lista.adicionar(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        menu();
        int opcao = sc.nextInt();
        while (true) {
            switch (opcao) {
                case 0: {
                    sc.close();
                    break;
                }
                case 1: {
                    System.out.println("Insira o número da nota fiscal: ");
                    listaNf.consultaNumeroNF(sc.next());
                    break;
                }
                case 2: {
                    System.out.println("\nNota mais cara: ");
                    consultaNotaMaisCara(listaNf);
                    break;
                }
                case 3: {
                    System.out.println("\nNota mais barata: ");
                    consultaNotaMaisBarata(listaNf);
                    break;
                }
                case 4: {
                    System.out.println("\nNota com mais itens: ");
                    consultaNotaMaisItens(listaNf);
                    break;
                }
                case 5: {
                    listaNf.imprimirLista();
                    break;
                }
            }
            menu();
            opcao = sc.nextInt();
        }

    }

    private void consultaNotaMaisCara(ListaNotaFiscal lista) {
        NotaFiscal atual = lista.getInicio().getProximo();
        double maxValor = 0;
        NotaFiscal notaMaisCara = null;
        while (atual != lista.getFim()) {
            double valorTotal = calcularValorTotalNota(atual);
            if (valorTotal > maxValor) {
                maxValor = valorTotal;
                notaMaisCara = atual;
            }
            atual = atual.getProximo();
        }
        if (notaMaisCara != null) {
            System.out.println("Nota fiscal mais cara: " + notaMaisCara.getNumero() + ", Valor total: " + maxValor);
        } else {
            System.out.println("Nenhuma nota fiscal encontrada.");
        }
    }

    private double calcularValorTotalNota(NotaFiscal nota) {
        ListaItemNotaFiscal itens = nota.getItens();
        double valorTotal = 0;
        ItemNotaFiscal atual = itens.getInicio();
        while (atual != null) {
            valorTotal += atual.getValorTotalItem();
            atual = atual.proximo;
        }
        return valorTotal;
    }

    private void consultaNotaMaisBarata(ListaNotaFiscal lista) {
        NotaFiscal atual = lista.getInicio().getProximo();
        double minValor = Double.MAX_VALUE;
        NotaFiscal notaMaisBarata = null;
        while (atual != lista.getFim()) {
            double valorTotal = calcularValorTotalNota(atual);
            if (valorTotal < minValor) {
                minValor = valorTotal;
                notaMaisBarata = atual;
            }
            atual = atual.getProximo();
        }
        if (notaMaisBarata != null) {
            System.out.println("Nota fiscal mais barata: " + notaMaisBarata.getNumero() + ", Valor total: " + minValor);
        } else {
            System.out.println("Nenhuma nota fiscal encontrada.");
        }

    }

    private void consultaNotaMaisItens(ListaNotaFiscal lista) {
        NotaFiscal atual = lista.getInicio().getProximo();
        int maxItens = 0;
        NotaFiscal notaMaisItens = null;
        while (atual != lista.getFim()) {
            int numItens = atual.getItens().getQuantidade();
            if (numItens > maxItens) {
                maxItens = numItens;
                notaMaisItens = atual;
            }
            atual = atual.getProximo();
        }
        if (notaMaisItens != null) {
            System.out.println("Nota fiscal com mais itens: " + notaMaisItens.getNumero() + ", Número de itens: " + maxItens);
        } else {
            System.out.println("Nenhuma nota fiscal encontrada.");
        }
    }
    private void menu() {
        System.out.println("Selecione a opcao desejada: ");
        System.out.println("[1] Consultar dados Nota Fiscal");
        System.out.println("[2] Exibir Nota Fiscal mais cara");
        System.out.println("[3] Exibir Nota Fiscal mais barata");
        System.out.println("[4] Exibir Nota Fiscal com mais itens");
        System.out.println("[5] Exibir todas Notas Fiscais");
        System.out.println("[0] Sair");
    }

}