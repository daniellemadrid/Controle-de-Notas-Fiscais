import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LeitorArquivos leitor = new LeitorArquivos();
        ListaItemNotaFiscal lista = new ListaItemNotaFiscal();

        try {
            leitor.lerArquivo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        exibirMenu(lista);
    }

    private static void exibirMenu(ListaItemNotaFiscal lista) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            lista.calcularTotalNotas();
            exibirOpcoesMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            processarOpcao(opcao, lista, scanner);
        } while (opcao != 6);

        System.out.println("Saindo do programa...");
        scanner.close();
    }

    private static void exibirOpcoesMenu() {
        System.out.println("______________Menu______________");
        System.out.println("1. Consultar dados de um NF");
        System.out.println("2. Exibir número da NF com maior valor");
        System.out.println("3. Exibir número da NF de menor valor");
        System.out.println("4. Exibir número da NF com mais itens");
        System.out.println("5. Listar todas as NF");
        System.out.println("6. Sair");
    }

    private static void processarOpcao(int opcao, ListaItemNotaFiscal lista, Scanner scanner) {
        switch (opcao) {
            case 1:
                consultarNF(lista, scanner);
                break;
            case 2:
                exibirMaiorValorNF(lista);
                break;
            case 3:
                exibirMenorValorNF(lista);
                break;
            case 4:
                exibirMaisItensNF(lista);
                break;
            case 5:
                listarTodasNF(lista);
                break;
            case 6:
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
        }
    }

    private static void consultarNF(ListaItemNotaFiscal lista, Scanner scanner) {
        System.out.println("Consultar dados de um NF");
        System.out.print("Informe o número da NF: ");
        scanner.nextLine();
        String num = scanner.nextLine();

        ItemNotaFiscal itemEncontrado = lista.encontrarNotaPorNumero(num);
        if (itemEncontrado != null) {
            System.out.println(itemEncontrado);
        } else {
            System.out.println("Nota fiscal não encontrada para o número informado.");
        }
    }

    private static void exibirMaiorValorNF(ListaItemNotaFiscal lista) {
        ItemNotaFiscal itemMaiorValor = null;
        double maiorValor = 0;

        ItemNotaFiscal notaAtual = lista.getInicio();
        while (notaAtual != null) {
            double valorTotalNota = 0;
            ItemNotaFiscal itemAtual = notaAtual;
            while (itemAtual != null) {
                valorTotalNota += itemAtual.getValorTotalItem();
                itemAtual = itemAtual.proximo;
            }
            if (valorTotalNota > maiorValor) {
                maiorValor = valorTotalNota;
                itemMaiorValor = notaAtual;
            }
            notaAtual = notaAtual.proximo;
        }

        if (itemMaiorValor != null) {
            System.out.println("Número da NF de maior valor: " + itemMaiorValor.getItemNumero());
        } else {
            System.out.println("Nenhuma Nota Fiscal encontrada na lista.");
        }
    }


    private static void exibirMenorValorNF(ListaItemNotaFiscal lista) {
        System.out.println("Exibir número da NF de menor valor: ");

        double menorValor = Double.MAX_VALUE;
        ItemNotaFiscal notaFiscalMenorValor = null;

        ItemNotaFiscal notaAtual = lista.getInicio();
        while (notaAtual != null) {
            ItemNotaFiscal itemAtual = notaAtual.proximo;
            while (itemAtual != null) {
                double valorTotalItem = itemAtual.getValorTotalItem();
                if (valorTotalItem < menorValor) {
                    menorValor = valorTotalItem;
                    notaFiscalMenorValor = itemAtual;
                }
                itemAtual = itemAtual.proximo;
            }
            notaAtual = notaAtual.proximo;
        }

        if (notaFiscalMenorValor != null) {
            System.out.println("Número da Nota Fiscal com o Menor Valor: " + notaFiscalMenorValor.getItemNumero());
        } else {
            System.out.println("Nenhuma Nota Fiscal encontrada na lista.");
        }
    }

    private static void exibirMaisItensNF(ListaItemNotaFiscal lista) {
        System.out.println("Exibir número da NF com mais itens: ");
        NotaFiscal nfMaiorItens = null;
        int maxItens = 0;

        ItemNotaFiscal notaAtual = lista.getInicio();
        while (notaAtual != null) {
            if (notaAtual.getQuantidade() > maxItens) {
                maxItens = notaAtual.getQuantidade();
//                nfMaiorItens = notaAtual;
            }
            notaAtual = notaAtual.proximo;
        }

        if (nfMaiorItens != null) {
            System.out.println("Número da Nota Fiscal com mais itens: " + nfMaiorItens.getNumero());
        } else {
            System.out.println("Nenhuma Nota Fiscal encontrada na lista.");
        }
    }

    private static void listarTodasNF(ListaItemNotaFiscal lista) {
        System.out.println("Listar todas as NF: ");
        System.out.println(lista.toString());
    }
}

