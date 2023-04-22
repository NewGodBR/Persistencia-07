public class Personagem {
    private String nome;
    private int atencao;

    public String getNome() {
        return nome;
    }

    public int getAtencao() {
        return this.atencao;
    }

    public void setAtencao(int atencao) {
        this.atencao += atencao;
        if (this.atencao > 100) {
            this.atencao = 100;
        } else if (this.atencao <= 0) {
            System.out.println(this.nome + " está completamente desfocado de sua tarefa");
        }
    }

    public Personagem(String nome, int atencao) {
        this.nome = nome;
        this.atencao = atencao;
    }
}