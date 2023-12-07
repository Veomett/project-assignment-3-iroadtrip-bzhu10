import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CapdistSon{
    public Integer numb;
    public String IDb;
    public Integer kmdist;
    public Integer midist;
    public CapdistSon(String ida){

    }

    public List<String[]> getIdbS(String ida){
        try(BufferedReader br = Files.newBufferedReader(Paths.get("file/capdist.csv"))){
            String DELIMITER = ",";
            String line;
            List<String[]> stringList = new ArrayList<>();
            while((line = br.readLine()) != null){
                String[] columns = line.split(DELIMITER);
                if(columns[1].toLowerCase().equals(ida.toLowerCase())){
                    stringList.add(columns);
                }
            }
            return stringList;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){

    }
}
