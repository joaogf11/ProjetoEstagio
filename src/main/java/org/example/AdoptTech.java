package org.example;

import com.google.gson.Gson;
import models.Crianca;
import utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class AdoptTech {
    private static final Set<Integer> generatedIds = new HashSet<>();

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Utils util = new Utils();
        util.verificationBank();
        ArrayList<Crianca> bancoDeCrianca = new ArrayList<>();
        int opcao;
        String linhas = "=".repeat(50);

        System.out.println("Bem-vindo ao AdoptTech!");

        do {
            util.printMenu(linhas);
            opcao = Integer.parseInt(new Scanner(System.in).nextLine());

            switch (opcao) {
                case 1:
                    Crianca crianca = util.validInput(linhas);
                    int id = util.generateUniqueID();
                    crianca.setId(id);
                    bancoDeCrianca.add(crianca);
                    try {
                        File file = new File("/home/fullcam/IdeaProjects/projeto1/criancas.json");
                        FileWriter fileWriter = new FileWriter(file);
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
                            System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", " +
                                    "Idade: " + c.getIdade() + " anos, Gênero: " + c.getGenero());
                        }
                    }
                    break;
                case 3:
                    System.out.println(linhas);
                    System.out.println("O AdoptTech tem como objetivo ajudar a encontrar lares para crianças.");
                    break;
                case 4:
                    System.out.println(linhas);
                    if (bancoDeCrianca.size() == 0) {
                        System.out.println("Não há crianças cadastradas.");
                    } else {
                        System.out.println("Selecione a criança que deseja remover:");
                        for (int i = 0; i < bancoDeCrianca.size(); i++) {
                            Crianca c = bancoDeCrianca.get(i);
                            System.out.println((i + 1) + ". ID: " + c.getId() + ", Nome: " + c.getNome() + ", " +
                                    "Idade: " + c.getIdade() + " anos, Gênero: " + c.getGenero());
                        }
                        int escolha = Integer.parseInt(new Scanner(System.in).nextLine());
                        if (escolha >= 1 && escolha <= bancoDeCrianca.size()) {
                            Crianca criancaRemovida = bancoDeCrianca.remove(escolha - 1);
                            System.out.println("A criança " + criancaRemovida.getNome() + " foi removida do banco de dados.");
                        } else {
                            System.out.println("Opção inválida. Nenhuma criança foi removida.");
                        }
                    }
                    break;
                case 5:
                    System.out.println(linhas);
                    System.out.println("Obrigado por usar o AdoptTech. Até a próxima!");
                    System.out.println(linhas);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }
}
