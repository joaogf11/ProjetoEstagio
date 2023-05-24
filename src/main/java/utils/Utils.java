package utils;

import com.google.gson.Gson;
import models.Crianca;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Utils {
    private static final Set<Integer> generatedIds = new HashSet<>();

    public void verificationBank() {
        Gson gson = new Gson();
        File file = new File("/home/fullcam/IdeaProjects/projeto1/criancas.json");
        if (file.exists()) {
            System.out.println("O banco já existe.");
        } else {
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                gson.toJson(new ArrayList<Crianca>(), fileWriter);
                fileWriter.flush();
                fileWriter.close();
                System.out.println("Banco criado com sucesso.");
            } catch (Exception ex) {
                System.out.println("Não foi possível criar o banco.");
                sleep(5);
                System.exit(0);
            }
        }
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (Exception exc) {
            System.out.println("Erro ao executar sleep.");
        }
    }

    public void printMenu() {
        linhas();
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Consultar");
        System.out.println("3 - Sobre");
        System.out.println("4 - Sair");
        linhas();
    }

    public void printOptions(){
        System.out.println("Escolha uma opção:");
        System.out.println("1- Pesquisar criança");
        System.out.println("2- Remover criança");
        System.out.println("3- Alterar cadastro");
        System.out.println("4- Voltar ao menu");
    }

    public Crianca validInput() {
        while (true) {
            try {
                linhas();
                System.out.print("Digite o nome da criança: ");
                String nome = new Scanner(System.in).nextLine();
                System.out.print("Digite a idade da criança: ");
                int idade = Integer.parseInt(new Scanner(System.in).nextLine());
                System.out.print("Digite o gênero da criança (masculino, feminino ou não-binário): ");
                String genero = new Scanner(System.in).nextLine();
                linhas();
                System.out.println(nome + " foi cadastrada com sucesso para adoção!");
                return new Crianca(nome, idade, genero);
            } catch (Exception ex) {
                System.out.println("Dado inválido. Tente novamente.");
            }
        }
    }

    public static int gerarId() {
        Random randomid = new Random();
        return randomid.nextInt(900) + 100;
    }

    public int generateUniqueID() {
        int id;
        do {
            id = generateRandomID();
        } while (generatedIds.contains(id));
        generatedIds.add(id);
        return id;
    }

    private int generateRandomID() {
        Random random = new Random();
        return random.nextInt(900) + 100;
    }

    public void searchById(ArrayList<Crianca> bancoDeCrianca){
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
    }

    public void removeById(ArrayList<Crianca> bancoDeCrianca) {
        if (bancoDeCrianca.isEmpty()) {
            System.out.println("Não há crianças cadastradas.");
        } else {
            System.out.println("Digite o ID da criança que deseja remover:");
            for (int i = 0; i < bancoDeCrianca.size(); i++) {
                Crianca c = bancoDeCrianca.get(i);
                System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", " +
                        "Idade: " + c.getIdade() + " anos, Gênero: " + c.getGenero());
            }
            int idRemover = Integer.parseInt(new Scanner(System.in).nextLine());
            boolean criancaEncontrada = false;
            Crianca criancaRemovida = null;
            for (Crianca c : bancoDeCrianca) {
                if (c.getId() == idRemover) {
                    criancaEncontrada = true;
                    criancaRemovida = c;
                    break;
                }
            }
            if (criancaEncontrada) {
                bancoDeCrianca.remove(criancaRemovida);
                System.out.println("A criança " + criancaRemovida.getNome() + " foi removida do banco de dados.");
            } else {
                System.out.println("ID inválido. Nenhuma criança foi removida. ");
            }
        }
    }

    public void linhas(){
        System.out.println("=".repeat(50));
    }
    public void putById(ArrayList<Crianca> bancoDeCrianca) {
        if (bancoDeCrianca.isEmpty()) {
            System.out.println("Não há crianças cadastradas.");
        } else {
            System.out.println("Selecione a criança que deseja alterar os dados:");
            for (int i = 0; i < bancoDeCrianca.size(); i++) {
                Crianca c = bancoDeCrianca.get(i);
                System.out.println((i + 1) + ". ID: " + c.getId() + ", Nome: " + c.getNome() + ", " +
                        "Idade: " + c.getIdade() + " anos, Gênero: " + c.getGenero());
            }
            int idAlterar = Integer.parseInt(new Scanner(System.in).nextLine());
            boolean criancaEncontrada = false;
            Crianca criancaAlterada = null;
            for (Crianca c : bancoDeCrianca) {
                if (c.getId() == idAlterar) {
                    criancaEncontrada = true;
                    criancaAlterada = c;
                    break;
                }
            }
            if (criancaEncontrada) {
                System.out.println("Digite os novos dados da criança:");
                Crianca novaCrianca = validInput();
                novaCrianca.setId(criancaAlterada.getId());
                bancoDeCrianca.set(bancoDeCrianca.indexOf(criancaAlterada), novaCrianca);
            } else {
                System.out.println("ID Inválido. Nenhuma criança foi alterada.");
            }
        }
    }
}
