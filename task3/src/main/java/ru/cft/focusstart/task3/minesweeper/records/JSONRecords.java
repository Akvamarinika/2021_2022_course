package ru.cft.focusstart.task3.minesweeper.records;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.view.GameType;
import java.io.*;
import java.util.*;

@Slf4j
@Getter
public class JSONRecords implements Records {
    private List<Gamer> bestGamers = new ArrayList<>(3);
    private final File file;
    private static final String FILE_NAME = "records.txt";
    private static final String DIR_NAME = "files/";
    private final ClassLoader classLoader = getClass().getClassLoader();
    private final Gson gson;

    public JSONRecords(){
        gson = new GsonBuilder()
                .setLenient()
                .create();

        ClassLoader classLoader = getClass().getClassLoader();
        String pathRes = Objects.requireNonNull(classLoader.getResource(DIR_NAME)).getPath();
        System.out.println(pathRes);
        file = new File(pathRes + FILE_NAME);
        System.out.println(file.getPath());
        createNewFileIfNotExists();
    }

    @Override
    public boolean isBestGamer(Gamer otherGamer){
        if (bestGamers.isEmpty() || !containsType(otherGamer.getGameType())){
            return true;
        }

        for (Gamer gamer : bestGamers){
            if ((gamer.getGameType() == otherGamer.getGameType()) && (gamer.getTime() > otherGamer.getTime())) {
                return true;
            }
        }
        return false;
    }

    private boolean containsType(GameType type){
        return bestGamers.stream()
                .map(Gamer::getGameType)
                .anyMatch(type::equals);
    }

    @Override
    public void addBestGamer(Gamer otherGamer){
        System.out.println(bestGamers);
        if (bestGamers.isEmpty() || !containsType(otherGamer.getGameType())){
            bestGamers.add(otherGamer);
            return;
        }

        replaceGamerWithBest(otherGamer);
    }

    private void replaceGamerWithBest(Gamer otherGamer){
        for (int idx = 0; idx < bestGamers.size(); idx++){
            if (bestGamers.get(idx).getGameType() == otherGamer.getGameType()) {
                bestGamers.set(idx, otherGamer);
            }
        }
    }

    @Override
    public void writeJsonInFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String gamerJson = gson.toJson(bestGamers);
            log.info("получен json: {}", gamerJson);
            writer.write(gamerJson);
            writer.flush();
        }
        catch(IOException ex){
            log.error("Ошибка при записи в файл рекордов. {}", ex.getMessage());
        }
    }

    @Override
    public void readRecordsFromFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null){
                parseJsonFromFile(line);
            }
        }
        catch(IOException ex){
            log.warn("Ошибка при чтении файла рекордов. {}", ex.getMessage());
        }
    }

    private void parseJsonFromFile(String jsonText){
        List<Gamer> arrayGson = Arrays.asList(gson.fromJson(jsonText, Gamer[].class));
        bestGamers = new ArrayList<>(arrayGson);
        log.info("parse json: {}", bestGamers);
    }

    private void createNewFileIfNotExists(){
       if (!file.isFile()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                log.error("Ошибка при создании файла рекордов {}", ex.getMessage());
            }
       }
    }
}
