import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StateName{
    public Integer statenumber;
    public String stateid; 
    public String countryname;
    public Date start;
    public Date end;
    public List<String[]> capdist;
    public Borders borders;
    public StateName(String countryname){
        this.countryname = countryname;
        List<String> list = new ArrayList<>();
        try{
            File file = new File("file/state_name.tsv");
            if(file.isFile() && file.exists()){
                InputStreamReader reader = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(reader);
                try{
                    String str;
                    int cnt = 0;
                    while((str = bufferedReader.readLine()) != null){
                        String[] split = str.trim().split("\t");
                        if(split[2].toLowerCase().contains(countryname.replaceAll("\\(.*?\\)", "").toLowerCase()) && split[4].equals("2020-12-31")){
                            this.statenumber = Integer.parseInt(split[0]);
                            this.stateid = split[1];
                            this.start = StringGetTime(split[3]);
                            this.end = StringGetTime(split[4]);
                            this.borders = new Borders(countryname);
                            this.capdist = getIdbS(this.stateid);
                            cnt = cnt + 1;
                        }
                        if(cnt != 0 && !split[2].toLowerCase().equals(countryname.toLowerCase())){
                            break;
                        }
                    }
                }catch(IOException e){
                    this.statenumber = null;
                    this.stateid = null;
                    this.start = null;
                    this.end = null;
                    this.borders = new Borders(countryname);
                    this.capdist = null;
                    e.printStackTrace();
                }
                bufferedReader.close();
                reader.close();
            }else{
                System.out.println("Unable to find the specified file");
            }
        }catch(IOException e){
            System.out.println("Error reading file content");
            e.printStackTrace();
        }
    }

    public StateName(Integer statenumber, String stateid, String countryname, Date start, Date end, List<String[]> capdist, Borders borders){
        this.statenumber = statenumber;
        this.stateid = stateid;
        this.countryname = countryname;
        this.start = start;
        this.end = end;
        this.capdist = capdist;
        this.borders = borders;
    }

    public static void main(String[] args){
        StateName canada = new StateName("United");
        System.out.println(canada.toString());
        for(String[] strings : canada.capdist){
            for(String string : strings){
                System.out.print(string + "   ");
            }
            System.out.println();
        }
    }

    public static Date StringGetTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            return sdf.parse(time);
        }catch(Exception e){
            throw new RuntimeException("Time conversion error");
        }
    }

    public String toString(){
        return "StateName{" +
                "statenumber=" + statenumber +
                ", stateid='" + stateid + '\'' +
                ", countryname='" + countryname + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", capdist=" + capdist +
                ", borders=" + borders.toString() +
                '}';
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
}
