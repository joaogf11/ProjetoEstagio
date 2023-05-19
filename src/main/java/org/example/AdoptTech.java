package org.example;

import com.google.gson.Gson;
import models.Crianca;
import utils.ManipulationFIle;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AdoptTech {
    private static final Set<Integer> generatedIds = new HashSet<>();

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Utils util = new Utils();
        ManipulationFIle manipulationFIle = new ManipulationFIle();
        util.verificationBank();
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

                    boolean continuar = true;
                    do {
                        System.out.println(linhas);
                        util.printOptions();
                        System.out.println(linhas);
                        int options = Integer.parseInt(new Scanner(System.in).nextLine());
                        switch (options) {
                            case 1:
                                System.out.println(linhas);
                                System.out.println("Digite o ID da criança que deseja pesquisar:");
                                int idPesquisa = Integer.parseInt(new Scanner(System.in).nextLine());
                                Crianca criancaEncontrada = null;
                                for (Crianca c : bancoDeCrianca) {
                                    if (c.getId() == idPesquisa) {
                                        criancaEncontrada = c;
                                        break;
                                    }
                                }
                                if (criancaEncontrada != null) {
                                    System.out.println("Criança encontrada:");
                                    System.out.println("ID: " + criancaEncontrada.getId() + ", Nome: " + criancaEncontrada.getNome() +
                                            ", Idade: " + criancaEncontrada.getIdade() + " anos, Gênero: " + criancaEncontrada.getGenero());
                                } else {
                                    System.out.println("Criança não encontrada.");
                                }
                                break;
                            case 2:
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
                            case 3:
                                System.out.println(linhas);
                                if (bancoDeCrianca.size() == 0) {
                                    System.out.println("Não há crianças cadastradas.");
                                } else {
                                    System.out.println("Selecione a criança que deseja alterar os dados:");
                                    for (int i = 0; i < bancoDeCrianca.size(); i++) {
                                        Crianca c = bancoDeCrianca.get(i);
                                        System.out.println((i + 1) + ". ID: " + c.getId() + ", Nome: " + c.getNome() + ", " +
                                                "Idade: " + c.getIdade() + " anos, Gênero: " + c.getGenero());
                                    }
                                    int escolha = Integer.parseInt(new Scanner(System.in).nextLine());
                                    if (escolha >= 1 && escolha <= bancoDeCrianca.size()) {
                                        Crianca criancaSelecionada = bancoDeCrianca.get(escolha - 1);
                                        System.out.println("Digite os novos dados da criança:");

                                        Crianca novaCrianca = util.validInput(linhas);
                                        novaCrianca.setId(criancaSelecionada.getId());

                                        bancoDeCrianca.set(escolha - 1, novaCrianca);
                                        System.out.println("Os dados da criança foram atualizados com sucesso!");
                                    } else {
                                        System.out.println("Opção inválida. Nenhuma criança foi alterada.");
                                    }
                                }
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
                    System.out.println(linhas);
                    System.out.println("O AdoptTech tem como objetivo ajudar a encontrar lares para crianças.");
                    break;
                case 4:
                    System.out.println(linhas);
                    System.out.println("Obrigado por usar o AdoptTech. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }
}
