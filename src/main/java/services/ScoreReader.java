package services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ScoreReader {

    public int readScoreFromUser(String path,String username){
        JSONParser parser = new JSONParser();
        int score = 0;
        try {
            System.out.println("Se buscan puntajes en: "+ path);
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path));
            for (Object jsonEntry : jsonArray){
                JSONObject scoreEntry = (JSONObject) jsonEntry;
                String user = scoreEntry.get("username").toString();
                if(user.equals(username)){
                    score = Integer.parseInt(scoreEntry.get("score").toString());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return score;
    }

    public Map<String, Integer> readAll(String path){
        JSONParser parser = new JSONParser();
        HashMap<String, Integer> scoreMap = new HashMap<>();
        try {
            System.out.println("Se buscan puntajes en: "+ path);
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path));
            for (Object jsonEntry : jsonArray){
                JSONObject scoreEntry = (JSONObject) jsonEntry;
                String user = scoreEntry.get("username").toString();
                int score = Integer.parseInt(scoreEntry.get("score").toString());
                scoreMap.put(user,score);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        LinkedHashMap<String,Integer> sortedScores = new LinkedHashMap<>();
        scoreMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(entry -> sortedScores.put(entry.getKey(),entry.getValue()));
        return sortedScores;
    }
}
