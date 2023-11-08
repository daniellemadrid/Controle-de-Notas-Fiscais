public class ListaItemNotaFiscal {
    private ItemNotaFiscal inicio;
    private ItemNotaFiscal fim;
    private int quantidade;

    public void adicionar(ItemNotaFiscal item) {
        if (quantidade == 0) {
            inicio = item;
            fim = item;
        } else {
            fim.proximo = item;
            fim = item;
        }
        quantidade++;
    }

    public ItemNotaFiscal encontrarNotaPorNumero(String numero) {
        ItemNotaFiscal atual = inicio;
        while (atual != null) {
            if (atual.getItemNumero().equalsIgnoreCase(numero)) {
                return atual;
            }
            atual = atual.proximo;
        }
        return null;
    }


    @Override
    public String toString() {
        String s = "";
        ItemNotaFiscal aux = inicio;
        while (aux != null) {
            s = s + aux + "\n";
            aux = aux.proximo;
        }
        return s;
    }

    public int getQuantidade() {
        return quantidade;
    }
}