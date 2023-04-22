import java.util.Scanner;
import java.util.HashMap;

public class Historia {
    public static void main(String[] args) throws Exception {
        Scanner escaneador = new Scanner(System.in);

        HashMap<String, Personagem> personagens = LeitorArquivos.lerPersonagens();
        HashMap<String, Cap> capitulos = LeitorArquivos.lerCapitulos();

        Cap cabeca = capitulos.get("encontrododesastre");
        cabeca.Mostrar();

        escaneador.close();
    }
}
