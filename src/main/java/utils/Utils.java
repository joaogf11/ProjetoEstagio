package utils;
import com.google.gson.Gson;
import models.Crianca;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public void verificationBank() {
        Gson gson = new Gson();
        File file = new File("/home/fullcam/IdeaProjects/javaAulas/untitled1/criancas.json");
        if (file.exists()) {
            System.out.println("O banco já existe.");

        } else {
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter("/home/fullcam/IdeaProjects/javaAulas/untitled1/criancas.json");
                gson.toJson(new ArrayList<Crianca>(), fileWriter);
                fileWriter.flush();
                fileWriter.close();
                System.out.println("Banco criado com sucesso.");
            } catch (Exception ex) {
                System.out.println("Não foi possivel criar o banco.");
                sleep(5);
                System.exit(0);
            }
        }
    }
    public void sleep(int seconds){
        try{
            Thread.sleep(1000*seconds);
        }
        catch (Exception exc){
            System.out.printf("Erro ao executar sleep.");

        }
    }
    public void printMenu(String linhas){
        System.out.println(linhas);
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Consultar");
        System.out.println("3 - Sobre");
        System.out.println("4 - Sair");
        System.out.println(linhas);
    }

    public Crianca validInput(String linhas){
        while (true){
            try{
                System.out.println(linhas);
                System.out.print("Digite o nome da criança: ");
                String nome = new Scanner(System.in).nextLine();
                System.out.print("Digite a idade da criança: ");
                int idade = Integer.parseInt(new Scanner(System.in).nextLine());
                System.out.print("Digite o gênero da criança (masculino, feminino ou não-binário): ");
                String genero = new Scanner(System.in).nextLine();
                System.out.println(linhas);
                System.out.println(nome + " foi cadastrada com sucesso para adoção!");
                return new Crianca(nome, idade, genero);
            }catch(Exception ex){
                System.out.println("Dado inválido. Tente novamente.");
            }


        }

    }
}
