package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ScoreWriter {
    public void writeScore(String path, String username, int score){
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        try{
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(path));
            JSONArray root = mapper.readValue(new File(path),JSONArray.class);

            int oldScore = 0;
            System.out.println(jsonArray.size());
            for(int i=0; i<jsonArray.size();i++){
                JSONObject scoreEntry = (JSONObject) jsonArray.get(i);
                if(scoreEntry.get("username").equals(username)){
                    oldScore = Integer.parseInt(scoreEntry.get("score").toString());
                    root.remove(i);
                }
                System.out.println(scoreEntry);
            }

            JSONObject newEntry = new JSONObject();
            newEntry.put("username", username);
            newEntry.put("score",score + oldScore);
            root.add(newEntry);

            try(FileWriter file = new FileWriter(path)){
                file.write(root.toString());
                file.flush();
                System.out.println("Succesful update");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
