package org.example;

import com.google.gson.Gson;
import models.Crianca;
import utils.ManipulationFIle;
import utils.Utils;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdoptTech {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Utils util = new Utils();
        ManipulationFIle manipulationFIle = new ManipulationFIle();
        util.verificationBank();
        //ArrayList<Crianca> bancoDeCrianca = new ArrayList<>();
        ArrayList<Crianca> bancoDeCrianca = manipulationFIle.readFile();
        int opcao;
        String linhas = "=".repeat(50);

        System.out.println("Bem-vindo ao AdoptTech!");

        do {
            util.printMenu(linhas);

            opcao = Integer.parseInt(new Scanner(System.in).nextLine());

            switch (opcao) {
                case 1:
                    Crianca crianca = util.validInput(linhas);
                    bancoDeCrianca.add(crianca);
                   try {
                        FileWriter fileWriter = new FileWriter("/home/fullcam/IdeaProjects/javaAulas/untitled1/criancas.json");
                        gson.toJson(bancoDeCrianca, fileWriter);
                        fileWriter.flush();
                        fileWriter.close();
                        System.out.println("Dados das crianças salvos com sucesso!");
                    } catch (Exception save) {
                        System.out.println("Erro ao salvar os dados das crianças.");
                        System.out.println(save.getMessage());
                    }
                    break;
                case 2:
                    System.out.println(linhas);
                    if (bancoDeCrianca.size() == 0) {
                        System.out.println("Não há crianças disponíveis para adoção.");
                    } else {
                        System.out.println("As seguintes crianças estão disponíveis para adoção:");
                        for (Crianca c : bancoDeCrianca) {
                            System.out.println("Nome - " + c.getNome() + ", " + c.getIdade() + " anos, " + c.getGenero());
                        }
                    }
                    break;
                case 3:
                    System.out.println(linhas);
                    System.out.println("O AdoptTech tem como objetivo ajudar a encontrar lares para crianças.");
                    break;
                case 4:
                    System.out.println(linhas);
                    System.out.println("Obrigado por usar o AdoptTech. Até a próxima!");
                    System.out.println(linhas);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 4);
    }
}
