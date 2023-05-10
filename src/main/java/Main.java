import java.io.File;

public class Main {
    public static void main(String[] args){
        String path = new File("").getAbsolutePath() +File.separator+"mock"+File.separator+"puntajes.json";
        ScoreService scoreService = new ScoreService(path);
    }
}
