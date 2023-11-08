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
        NotaFiscal atual = inicio.getProximo();
        while (atual != fim && nf.getData().after(atual.getData())) {
            atual = atual.getProximo();
        }

        nf.setProximo(atual);
        nf.setAnterior(atual.getAnterior());
        atual.getAnterior().setProximo(nf);
        atual.setAnterior(nf);
        quantidade++;
    }

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
            System.out.println("CNPJ/CPF: " + atual.getCnpjCpf() + ", Endereço: " + atual.getEndereco() + ", Cidade: " + atual.getCidade() + ", Estado: " + atual.getEstado());
            System.out.println("Itens da Nota Fiscal: ");
            System.out.println(atual.getItens().toString());
            System.out.println("-------------------------------------------");
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