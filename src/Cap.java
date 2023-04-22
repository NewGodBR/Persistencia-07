import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Cap {
    private String texto;
    private ArrayList<Escolha> escolhas;
    
    private Personagem personagem;
    private int atencao;
    
    public Cap(String texto, ArrayList<Escolha> escolhas, Personagem personagem, int atencao) {
        this.texto = texto;
        this.escolhas = escolhas;
        this.personagem = personagem;
        this.atencao = atencao;
    }
    public void setEscolhas(ArrayList<Escolha> escolhas) {
        this.escolhas = escolhas;
    }
    
    public void Mostrar() {
        this.personagem.setAtencao(this.atencao);
        System.out.println(
                "-----------\n" +
                        this.texto + "\n" +
                        this.personagem.getNome() + ": " + this.personagem.getAtencao());
        if (escolhas != null) {
            int i = 1;
            for (Escolha escolha : escolhas) {
                System.out.println(i + ". [" + escolha.getNome() + "]");
                i++;
            }
            Scanner scanner = new Scanner(System.in);
            String escolhaJog = scanner.nextLine();
            for (Escolha escolha : escolhas) {
                if (escolhaJog.equals(escolha.getNome())) {
                    Cap proximo = escolha.getProximo();
                    if (proximo != null) {
                        proximo.Mostrar();
                        return;
                    } else {
                        System.out.println("Escolha inválida");
                    }
                }
                scanner.close();
            }
        }
    }
}