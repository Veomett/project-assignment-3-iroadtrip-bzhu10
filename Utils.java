import java.io.*;
import java.util.*;
public class Utils{
    public static void main(String[] args){
        System.out.println(getStringOneSet("Spain (Ceuta)"));

    }
    public static Map<String,Set<String>> getStringMap(Set<String> nameAll){
        Map<String,Set<String>> stringSetMap = new HashMap<>();
        HashSet<String> spain = new HashSet<>();
        spain.add("Andorra");
        spain.add("France");
        spain.add("Gibraltar");
        spain.add("Portugal");
        spain.add("Morocco (Ceuta)");
        stringSetMap.put("Spain (Ceuta)",spain);
        for(String s :nameAll){
            stringSetMap.put(s.trim(),getStringOneSet(s));
        }
        return stringSetMap;
    }

    public static Set<String> getStringOneSet(String name){
        Set<String> strings = new HashSet<>();

        try{
            FileInputStream fis = new FileInputStream("file/borders.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while((line = br.readLine()) != null){
                String[] split = line.split(" = ");
                if(split[0].equals(name) && split.length > 1){
                    String[] strSplit = split[1].split(";");
                    for(String str:strSplit){
                        String key = str.substring(0, str.lastIndexOf(" ", str.lastIndexOf(" ") - 2)).trim();
                        strings.add(key.trim());
                    }

                }

            }
            br.close();
            fis.close();
        }catch (Exception e){
            throw new RuntimeException("read failure");
        }
        return strings;
    }

    public static Set<String> getStringSet(){
        List<String> strings = new ArrayList<>();
        Set<String> stringSet = new HashSet<>();
        try{
            FileInputStream fis = new FileInputStream("file/borders.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while((line = br.readLine()) != null){
                strings.add(line);
            }
            br.close();
            fis.close();
        }catch(Exception e){
            throw new RuntimeException("read failure");
        }

        for(String s: strings){
            String[] split = s.split(" = ");
            String s1 = split[0];
            stringSet.add(s1.trim());
            if(split.length > 1){
                String s2 = split[1];
                String[] split1 = s2.split(";");
                for(String str: split1){
                    str = str.trim();
                    stringSet.add(str.split(" ")[0].trim());
                }
            }
        }
        return stringSet;
    }
}
