import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class LeitorArquivos {
    public static HashMap<String, Cap> lerCapitulos() {

        HashMap<String, Cap> caps = new HashMap<String, Cap>();
        try {
            File myFile = new File("C:/Users/Amd/Desktop/Projetos - Programação/mama/lib/Capitulos.txt");
            Scanner myReader = new Scanner(myFile,"UTF-8");

            String titulo = "";
            String historia = "";
            ArrayList<Escolha> escolhas = new ArrayList<Escolha>();
            Personagem personagem = null;
            int alteracaoAtencao = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.matches("[a-zA-Z]+")) {
                    titulo = data;
                } else if (data.startsWith("Historia: ")) {
                    historia = data.substring(10);
                    // Lê a história até encontrar a linha vazia
                    while (myReader.hasNextLine()) {
                        String linha = myReader.nextLine();
                        if (linha.startsWith("Escolhas:")) {
                            break;
                        } else {
                            historia += "\n" + linha;
                        }
                    }
                }
                    
                 else if (data.startsWith("Personagem: ")) {
                    String chavePersonagem = data.substring(12);
                    personagem = new Personagem(
                            LeitorArquivos.lerPersonagens().get(chavePersonagem).getNome(),
                            LeitorArquivos.lerPersonagens().get(chavePersonagem).getAtencao());

                } else if (data.startsWith("Alteracao: ")) {
                    alteracaoAtencao = Integer.parseInt(data.substring(12));
                    Cap cap = new Cap(historia, escolhas, personagem, alteracaoAtencao);
                    caps.put(titulo, cap);
                    // Reinicia as variáveis para ler o próximo capítulo
                    titulo = "";
                    historia = "";
                    escolhas = new ArrayList<Escolha>();
                    personagem = null;
                    alteracaoAtencao = 0;
                }
            } 
            int count = 0;
            Scanner choseReader = new Scanner(myFile);
            while(choseReader.hasNextLine()){
                String data = choseReader.nextLine();
                ArrayList<Escolha> escolhaX = new ArrayList<Escolha>();

                if (data.startsWith("Escolhas: ")){
                    String stringEscolhas = data.substring(10);
                    if (stringEscolhas.equalsIgnoreCase("0")){
                        escolhaX = null;
                    }
                    else{
                        // texto:CapProx texto:CapProx
                        String[] arrayEscolhas = stringEscolhas.split(" ");
                        for (String stringEscolha : arrayEscolhas){
                            String[] escolha = stringEscolha.split(":");
                            escolhaX.add(new Escolha(escolha[0],caps.get(escolha[1])));
                        }
                    }
                    count += 1;
                    if (count == 1){
                        caps.get("encontrododesastre").setEscolhas(escolhaX);
                    }
                    else if(count == 2){
                        caps.get("continua").setEscolhas(escolhaX);
                    }
                    else if(count == 3){
                        caps.get("ligacao").setEscolhas(escolhaX);
                    }
                    else if(count == 4){
                        caps.get("casamento").setEscolhas(escolhaX);
                    }
                    else if(count == 5){
                        caps.get("missao").setEscolhas(escolhaX);
                    }
                } 
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.printStackTrace();
        }

        return caps;

    }

    public static HashMap<String, Personagem> lerPersonagens() {

        HashMap<String, Personagem> personagens = new HashMap<String, Personagem>();
        try {
            File myFile = new File("C:/Users/Amd/Desktop/Projetos - Programação/mama/lib/Personagens.txt");
            Scanner myReader = new Scanner(myFile);

            String chave = "";
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.matches("[a-zA-Z]+")) {
                    chave = data;
                } else if (data.startsWith("Nome: ")) {
                    String nome = data.substring(6);
                    data = myReader.nextLine();
                    if (data.startsWith("Atencao: ")) {
                        int mana = Integer.parseInt(data.substring(9));
                        Personagem personagem = new Personagem(nome, mana);
                        personagens.put(chave, personagem);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.printStackTrace();
        }
        return personagens;

    }
}