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

    public void calcularTotalNotas() {
        double totalNotas = 0;
        ItemNotaFiscal notaAtual = this.inicio;

        while (notaAtual != null) {
            ItemNotaFiscal itemAtual = notaAtual;

            while (itemAtual != null) {
                totalNotas += itemAtual.getValorTotalItem();
                itemAtual = itemAtual.proximo;
            }

            notaAtual = notaAtual.proximo;
        }

        System.out.println("Quantidade de notas carregadas: " + this.quantidade);
        System.out.println("Valor total de todas as notas: " + totalNotas);
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

    public ItemNotaFiscal getInicio() {
            return inicio;
    }
}