import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

public class LeitorArquivos {

    public ListaItemNotaFiscal lerArquivo() throws IOException {
        String linha;
        String arquivo = "src/notas_fiscais_00100.csv";
        ListaItemNotaFiscal lista = new ListaItemNotaFiscal();
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            leitor.readLine(); // Ignora o cabeçalho
            linha = leitor.readLine();
            String[] colunas = linha.split("[|]");
            String notaAnterior = colunas[0];
            NotaFiscal nf = new NotaFiscal();
            nf.setNumero(colunas[0]);
            nf.setData(Date.valueOf(colunas[1]));
            nf.setCliente(colunas[2]);
            ListaItemNotaFiscal items = new ListaItemNotaFiscal();
            nf.setItens(items);
//            lista.adicionar(nf); // Adiciona a primeira nota fiscal à lista geral
            while ((linha = leitor.readLine()) != null) {
                colunas = linha.split("[|]");
                String notaAtual = colunas[0];
                if (!notaAnterior.equals(notaAtual)) {
                    nf = new NotaFiscal();
                    nf.setNumero(colunas[0]);
                    nf.setData(Date.valueOf(colunas[1]));
                    nf.setCliente(colunas[2]);
                    items = new ListaItemNotaFiscal();
                    nf.setItens(items);
//                    lista.adicionar(nf);
                    notaAnterior = notaAtual;
                }
                ItemNotaFiscal item = new ItemNotaFiscal(
                        colunas[7],
                        colunas[8],
                        Integer.parseInt(colunas[9]),
                        Double.parseDouble(colunas[10]));
                items.adicionar(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
