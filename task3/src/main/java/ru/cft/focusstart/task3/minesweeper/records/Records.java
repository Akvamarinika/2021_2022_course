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
public class Records {
    private List<Gamer> bestGamers = new ArrayList<>(3);
    private File file;
    private Gson gson;

//    KeyGenerator keyGenerator;
//    SecretKey desKey;
//    Cipher desCipher;

    public Records(){
        //file = new File( Objects.requireNonNull(getClass().getClassLoader().getResource("records.txt")).getPath());
        gson = new GsonBuilder()
                .setLenient()
                .create();
//        keyGenerator = KeyGenerator.getInstance("DES");
//        desKey = keyGenerator.generateKey();
//        URL resource = Records.class.getResource("records.txt");
//        Paths.get(resource.toURI()).toFile();

        ClassLoader classLoader = getClass().getClassLoader();
        String path = Objects.requireNonNull(classLoader.getResource("files/records.txt")).getPath();
        file = new File(path);

    }



//    public Map<GameType, String[]> getBestGamers() {
//        return bestGamers;
//    }
//
//    public void setBestGamers(Map<GameType, String[]> bestGamers) {
//        this.bestGamers = bestGamers;
//    }

//    public List<String[]> readRecordsFromFile(){
//        createNewFileIfNotExists();
//
//        List<String[]> bestGamers = new ArrayList<>(3);
//        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while((line = reader.readLine()) != null){
//                // if (line.matches("(.+\\s+){2}\\d+")){
//                String[] record = line.trim().split("\\s+");
//                bestGamers.add(record);
//            }
//        }
//        catch(IOException ex){
//            log.error("Ошибка при чтении файла рекордов.");
//        }catch (IllegalArgumentException ex){
//            log.warn("Тип игры в файле рекордов не распознан");
//        }
//       return bestGamers;
//    }
//
//    public void replaceRecordInFile(GameType gameType, String name, int time){
//        String winner = name;
//        if (winner.isEmpty()){
//            winner = "Unknown";
//        }
//
//        if (file.length() == 0){
//            String bestGamer = String.join(" ", gameType.name(), winner, String.valueOf(time));
//            writeInFile(bestGamer);
//        }
//        //createNewFileIfNotExists();
//
//        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while((line = reader.readLine()) != null){
//               // if (line.matches("(.+\\s+){2}\\d+")){
//                    String[] record = line.trim().split("\\s+");
//                    if (gameType.name().equals(record[0]) && time < Integer.parseInt(record[2])) {
//                        String newBestGamer = String.join(" ", gameType.name(), winner, String.valueOf(time));
//                        builder.append(newBestGamer)
//                                .append("\n");
//                        log.info("{}: Gamer {}, время {} заменен на {}: Gamer {}, время {}",
//                                record[0], record[1], record[2], gameType.name(), winner, time);
//                        continue;
//
//                    }
//                        builder.append(line)
//                                .append("\n");
//                log.info("{}: Gamer {}, время {} добавлен в файл", gameType.name(), winner, time);
//                    //bestGamers.put(GameType.valueOf(record[0]), record);
//               // }
//            }
//            System.out.println(builder.toString());
//            writeInFile(builder.toString());
//        }
//        catch(IOException ex){
//            log.error("Ошибка при чтении файла рекордов.");
//        }catch (IllegalArgumentException ex){
//            log.warn("Тип игры в файле рекордов не распознан");
//        }
//       // return builder;
//    }
//
//    public void replaceGamerInRecordsIfBest(GameType gameType, String name, int time){
//        if (file.length() > 0 && bestGamers.containsKey(gameType)){
//            //readRecords();
//            String[] record = bestGamers.get(gameType);
//            if (time < Integer.parseInt(record[2])){
//                String[] newRecord = new String[3];
//                newRecord[0] = gameType.name();
//                newRecord[1] = name;
//                newRecord[2] = String.valueOf(time);
//                bestGamers.replace(gameType, newRecord);
//            }
//        }
//        //writeInFile();
//    }

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

    public void addBestGamer(Gamer otherGamer){
        if (bestGamers.isEmpty() || !containsType(otherGamer.getGameType())){
            bestGamers.add(otherGamer);
            return;
        }

        replaceGamerWithBest(otherGamer);
    }

    private void replaceGamerWithBest(Gamer otherGamer){
        //bestGamers = readRecordsFromFile();
        for (int idx = 0; idx < bestGamers.size(); idx++){
            if (bestGamers.get(idx).getGameType() == otherGamer.getGameType()) {
                bestGamers.set(idx, otherGamer);
            }
        }
    }

//    public void replaceGamerIfBestTime(Gamer otherGamer){
//        List<Gamer> bestGamers = readRecordsFromFile();
//        if (!bestGamers.isEmpty()){
//            for (int idx = 0; idx < bestGamers.size(); idx++){
//                if (bestGamers.get(idx).getGameType() == otherGamer.getGameType() &&
//                        bestGamers.get(idx).getTime() > otherGamer.getTime()) {
//                    bestGamers.set(idx, otherGamer);
//                }
//            }
//        }
//    }

    public void writeJsonInFile(){
        //Gson gson = new Gson();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String gamerJson = gson.toJson(bestGamers);
            log.info("получен json: {}", gamerJson);
            writer.write(gamerJson);
        }
        catch(IOException ex){
            log.error("Ошибка при записи в файл рекордов. {}", ex.getMessage());
        }
    }

    public void readRecordsFromFile(){
        createNewFileIfNotExists();


        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null){
                //Gamer gamer = parseJsonFromFile(line);
                //bestGamers.add(gamer);
                System.out.println(line);
                parseJsonFromFile(line);
            }
        }
        catch(IOException ex){
            log.warn("Ошибка при чтении файла рекордов. {}", ex.getMessage());
        }
    }

    private void parseJsonFromFile(String jsonText){
        //Gson gson = new Gson();
//        Gamer gamer = gson.fromJson(jsonText, Gamer.class);
//        System.out.println(gamer);
        bestGamers = Arrays.asList(gson.fromJson(jsonText,
                Gamer[].class));
        //Type listType = new TypeToken<List<Gamer>>() {}.getType();
        //bestGamers = gson.fromJson(jsonText, listType);
        log.info("parse json: {}",bestGamers);
        //Gamer gamer = gson.fromJson(jsonText, Gamer);
        //log.info("obj Gamer: {}", gamer);
        //return gamer;
    }

//    public void writeInFile(String gamersStr){
//        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            writer.write(gamersStr);
////            for (String[] record : bestGamers.values()) {
////                writer.write(String.join(" ", record));
////            }
//        }
//        catch(IOException ex){
//            log.error("Ошибка при записи в файл рекордов.");
//        }
//    }

    public void createNewFileIfNotExists(){
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                log.error("Ошибка при создании файла рекордов {}", ex.getMessage());
            }
        }
    }

//    private void recordsEncrypted(String text){
//        byte[] textEncrypted;
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
//            SecretKey desKey = keyGenerator.generateKey();
//            Cipher desCipher = Cipher.getInstance("DES");
//
//            byte[] textBytes = text.getBytes("UTF8");
//            desCipher.init(Cipher.ENCRYPT_MODE, desKey);
//            textEncrypted = desCipher.doFinal(textBytes);
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
//            e.printStackTrace();
//        }

 //   }

    private void recordsDecrypted(){

    }



}
