
public class ListaNotaFiscal {
    private NotaFiscal inicio;
    private NotaFiscal fim;
    private int quantidade;
    public ListaNotaFiscal() {
        inicio = new NotaFiscal();
        fim = new NotaFiscal();
        inicio.setProximo(fim);
        fim.setAnterior(inicio);
    }
    public void adicionar(NotaFiscal nf) {
        nf.setProximo(fim);
        nf.setAnterior(fim.getAnterior());
        fim.getAnterior().setProximo(nf);
        fim.setAnterior(nf);
        quantidade++;
    }
    public NotaFiscal buscarNotaFiscal(String numeroNF) {
        NotaFiscal current = inicio.getProximo();
        while (current != fim) {
            if (current.getNumero().equals(numeroNF)) {
                return current;
            }
            current = current.getProximo();
        }
        return null;
    }


/* Consultar dados de um NF
a. O usuário informa o número da NF e a aplicação mostra os itens da nota
2) Exibir número da NF com maior valor.
3) Exibir número da NF de menor valor.
4) Exibir número da NF com mais itens.
5) Listar todas as NF
*/
}
