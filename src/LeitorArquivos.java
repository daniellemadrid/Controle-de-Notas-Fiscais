import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

public class LeitorArquivos {
    public void lerArquivo() throws IOException {
        String linha;
        String arquivo = "src/notas_fiscais_00100.csv";
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            leitor.readLine();
            linha = leitor.readLine();
            String[] colunas = linha.split("[|]");
            String notaAnterior = colunas[0];
            NotaFiscal nf = new NotaFiscal();
            nf.setNumero(colunas[0]);
            nf.setData(Date.valueOf(colunas[1]));
            nf.setCliente(colunas[2]);
            ListaItemNotaFiscal items = new ListaItemNotaFiscal();
            nf.setItens(items);
            while ((linha = leitor.readLine()) != null) {
                colunas = linha.split("[|]");
                String notaAtual = colunas[0];
                if (!notaAnterior.equals(notaAtual)) {
                    System.out.println("Nota Fiscal: " + nf.getNumero());
                    System.out.println("Data: " + nf.getData());
                    System.out.println("Cliente: " + nf.getCliente());
                    System.out.println("Itens da Nota Fiscal: ");
                    System.out.println(nf.getItens().toString()); // Imprimir lista de itens
                    System.out.println("-----------------------------------");
                    nf = new NotaFiscal();
                    nf.setNumero(colunas[0]);
                    nf.setData(Date.valueOf(colunas[1]));
                    nf.setCliente(colunas[2]);
                    items = new ListaItemNotaFiscal();
                    nf.setItens(items);
                    notaAnterior = notaAtual;
                }
                ItemNotaFiscal item = new ItemNotaFiscal(
                        colunas[7],
                        colunas[8],
                        Integer.parseInt(colunas[9]),
                        Double.parseDouble(colunas[10]));
                items.adicionar(item);
            }
            System.out.println("Nota Fiscal: " + nf.getNumero());
            System.out.println("Data: " + nf.getData());
            System.out.println("Cliente: " + nf.getCliente());
            System.out.println("Itens da Nota Fiscal: ");
            System.out.println(nf.getItens().toString()); // Imprimir lista de itens
            System.out.println("-----------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


