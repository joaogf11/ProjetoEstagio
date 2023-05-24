package org.example;

import com.google.gson.Gson;
import models.Crianca;
import utils.ManipulationFIle;
import utils.Utils;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AdoptTech {
    private static final Set<Integer> generatedIds = new HashSet<>();

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Utils util = new Utils();
        ManipulationFIle manipulationFile = new ManipulationFIle();
        util.verificationBank();
        ArrayList<Crianca> bancoDeCrianca = manipulationFile.readFile();
        int opcao;


        System.out.println("Bem-vindo ao AdoptTech!");

        do {
            util.linhas();
            util.printMenu();
            opcao = Integer.parseInt(new Scanner(System.in).nextLine());

            switch (opcao) {
                case 1:
                    Crianca crianca = util.validInput();
                    int id = util.generateUniqueID();
                    crianca.setId(id);
                    bancoDeCrianca.add(crianca);
                    try {
                        FileWriter fileWriter = new FileWriter("/home/fullcam/IdeaProjects/projeto1/criancas.json");
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
                    util.linhas();
                    if (bancoDeCrianca.size() == 0) {
                        System.out.println("Não há crianças disponíveis para adoção.");
                    } else {
                        System.out.println("As seguintes crianças estão disponíveis para adoção:");
                        for (Crianca c : bancoDeCrianca) {
                            System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", " +
                                    "Idade: " + c.getIdade() + " anos, Gênero: " + c.getGenero());
                        }
                    }

                    boolean continuar = true;
                    do {
                        util.linhas();
                        util.printOptions();
                        util.linhas();
                        int options = Integer.parseInt(new Scanner(System.in).nextLine());
                        switch (options) {
                            case 1:
                                util.linhas();
                                util.searchById(bancoDeCrianca);
                                break;
                            case 2:
                                util.linhas();
                                util.removeById(bancoDeCrianca);
                                manipulationFile.saveToFile(bancoDeCrianca);
                                break;
                            case 3:
                                util.linhas();
                                util.putById(bancoDeCrianca);
                                manipulationFile.saveToFile(bancoDeCrianca);
                                break;
                            case 4:
                                continuar = false;
                                opcao = 0;
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    } while (continuar);
                    break;
                case 3:
                    util.linhas();
                    System.out.println("O AdoptTech tem como objetivo ajudar a encontrar lares para crianças.");
                    break;
                case 4:
                    util.linhas();
                    System.out.println("Obrigado por usar o AdoptTech. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }
}
