package ru.cft.focusstart.task3.minesweeper.records;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task3.minesweeper.view.GameType;
import ru.cft.focusstart.task3.minesweeper.view.HighScoresWindow;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Records {
    Map<GameType, String[]> bestGamers = new HashMap<>(3);
    private File file = new File("records.txt");
    private HighScoresWindow highScoresWindow;
    private StringBuilder builder = new StringBuilder();
//    KeyGenerator keyGenerator;
//    SecretKey desKey;
//    Cipher desCipher;

    public Records()  {
//        keyGenerator = KeyGenerator.getInstance("DES");
//        desKey = keyGenerator.generateKey();

    }

    public Map<GameType, String[]> getBestGamers() {
        return bestGamers;
    }

    public void setBestGamers(Map<GameType, String[]> bestGamers) {
        this.bestGamers = bestGamers;
    }

    public List<String[]> readRecordsFromFile(){
        createNewFileIfNotExists();

        List<String[]> bestGamers = new ArrayList<>(3);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null){
                // if (line.matches("(.+\\s+){2}\\d+")){
                String[] record = line.trim().split("\\s+");
                bestGamers.add(record);
            }
        }
        catch(IOException ex){
            log.error("Ошибка при чтении файла рекордов.");
        }catch (IllegalArgumentException ex){
            log.warn("Тип игры в файле рекордов не распознан");
        }
       return bestGamers;
    }

    public void replaceRecordInFile(GameType gameType, String name, int time){
        String winner = name;
        if (winner.isEmpty()){
            winner = "Unknown";
        }

        if (file.length() == 0){
            String bestGamer = String.join(" ", gameType.name(), winner, String.valueOf(time));
            writeInFile(bestGamer);
        }
        //createNewFileIfNotExists();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null){
               // if (line.matches("(.+\\s+){2}\\d+")){
                    String[] record = line.trim().split("\\s+");
                    if (gameType.name().equals(record[0]) && time < Integer.parseInt(record[2])) {
                        String newBestGamer = String.join(" ", gameType.name(), winner, String.valueOf(time));
                        builder.append(newBestGamer)
                                .append("\n");
                        log.info("{}: Gamer {}, время {} заменен на {}: Gamer {}, время {}",
                                record[0], record[1], record[2], gameType.name(), winner, time);
                        continue;

                    }
                        builder.append(line)
                                .append("\n");
                log.info("{}: Gamer {}, время {} добавлен в файл", gameType.name(), winner, time);
                    //bestGamers.put(GameType.valueOf(record[0]), record);
               // }
            }
            System.out.println(builder.toString());
            writeInFile(builder.toString());
        }
        catch(IOException ex){
            log.error("Ошибка при чтении файла рекордов.");
        }catch (IllegalArgumentException ex){
            log.warn("Тип игры в файле рекордов не распознан");
        }
       // return builder;
    }

    public void replaceGamerInRecordsIfBest(GameType gameType, String name, int time){
        if (file.length() > 0 && bestGamers.containsKey(gameType)){
            //readRecords();
            String[] record = bestGamers.get(gameType);
            if (time < Integer.parseInt(record[2])){
                String[] newRecord = new String[3];
                newRecord[0] = gameType.name();
                newRecord[1] = name;
                newRecord[2] = String.valueOf(time);
                bestGamers.replace(gameType, newRecord);
            }
        }
        //writeInFile();
    }

    public void writeInFile(String gamersStr){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(gamersStr);
//            for (String[] record : bestGamers.values()) {
//                writer.write(String.join(" ", record));
//            }
        }
        catch(IOException ex){
            log.error("Ошибка при записи в файл рекордов.");
        }
    }

    public void createNewFileIfNotExists(){
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                log.error("Ошибка при создании файла рекордов");
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
