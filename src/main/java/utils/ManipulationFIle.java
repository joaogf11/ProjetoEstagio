package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Crianca;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

    public class ManipulationFIle {
    public ArrayList<Crianca> readFile(){
        ArrayList<Crianca> bancoDeCrianca = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("criancas.json");
            Type criancaListType = new TypeToken<ArrayList<Crianca>>(){}.getType();
            bancoDeCrianca = new Gson().fromJson(fileReader, criancaListType);
            System.out.println("Dados carregados com sucesso!");
            fileReader.close();
        } catch (Exception load) {
            System.out.println("Erro ao carregar os dados das crianças.");
            load.printStackTrace();
        }
        return bancoDeCrianca;
    }

    public void saveToFile(ArrayList<Crianca> bancoDeCrianca) {
        readFile();
        try {
            FileWriter fileWriter = new FileWriter("criancas.json");
            new Gson().toJson(bancoDeCrianca, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception save) {
            System.out.println("Erro ao salvar os dados das crianças.");
            save.printStackTrace();
        }
    }
}
