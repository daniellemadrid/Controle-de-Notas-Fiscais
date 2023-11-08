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
    public void consultaNumeroNF(int notaFiscal) {}

    public NotaFiscal getInicio() {
        return inicio;
    }

    public NotaFiscal getFim() {
        return fim;
    }

    public void imprimirLista() {
        NotaFiscal atual = inicio.getProximo();
        while (atual != fim) {
            System.out.println("Número: " + atual.getNumero() + ", Data: " + atual.getData() + ", Cliente: " + atual.getCliente());
            atual = atual.getProximo();
        }
    }

    public void consultaNumeroNF(String numeroNF) {
        NotaFiscal atual = inicio.getProximo();
        while (atual != fim) {
            if (atual.getNumero().equals(numeroNF)) {
                System.out.println("Número: " + atual.getNumero() + ", Data: " + atual.getData() + ", Cliente: " + atual.getCliente());
                System.out.println(atual.getItens().toString());
                return;
            }
            atual = atual.getProximo();
        }
        System.out.println("Nota Fiscal não encontrada.");
    }
}