import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Borders{
    public String bordersName;
    public Map<String,String> country = new HashMap<String, String>();
    public Borders(String bordersName){
        this.bordersName = bordersName;
        this.country = getMap(bordersName);
    }

    public Borders(String bordersName, Map<String, String> country){
        this.bordersName = bordersName;
        this.country = country;
    }

    public static Map<String,String> getMap(String countryname){
        String pathname = "file/borders.txt";
        try(FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ){
            String line;
            Map<String,String> map = new HashMap<>();
            one: while((line = br.readLine()) != null){
                String[] strs = line.split(" = ");
                if(strs[0].toLowerCase().contains(countryname.toLowerCase())){
                    if(strs.length==1){
                        map = new HashMap<>();
                    }else{
                        String[] strings = strs[1].split("; ");
                        for(String path:strings){
                            String key = path.substring(0, path.lastIndexOf(" ", path.lastIndexOf(" ") - 2));
                            String value = path.substring(path.lastIndexOf(" ", path.lastIndexOf(" ") - 1) + 1);
                            map.put(key.trim(),value.trim());
                        }
                        break one;
                    }
                }
            }
            return map;
        }catch(IOException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public String toString(){
        return "Borders{" +
                "bordersName='" + bordersName + '\'' +
                ", country=" + country +
                '}';
    }

    public static void main(String[] args){
        Borders borders = new Borders("");
        borders.country.keySet();
    }


}
