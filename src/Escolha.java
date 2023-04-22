public class Escolha {
    private String nome;
    private Cap proximo;

    public String getNome() {
        return nome;
    }

    public Cap getProximo() {
        return proximo;
    }

    public void setCapProx(Cap proximo) {
        this.proximo = proximo;
    }

    public Escolha(String texto, Cap proximo) {
        this.nome = texto;
        this.proximo = proximo;
    }

}