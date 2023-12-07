import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class IRoadTrip{


    public IRoadTrip(String[] args){
        // Replace with your code

    }


    public int getDistance(String country1, String country2){
        // Replace with your code
        try{
            StateName stateName1 = new StateName(country1);
            StateName stateName2 = new StateName(country2);
            Borders borders = stateName1.borders;
            Set<String> strings = borders.country.keySet();
            if(strings.contains(country2)){
                List<String[]> capdist = stateName1.capdist;
                for(String[] strs : capdist){
                    if(strs[3].equals(stateName2.stateid)){
                        return Integer.parseInt(strs[4]);
                    }
                }
            }
            return -1;
        }catch(Exception e){
            return -1;
        }
    }


    public static List<String> findPath(String country1, String country2){
        // Replace with your code
        List<String> stringList = new ArrayList<>();
        try{
            StateName stateName1 = new StateName(country1.replaceAll("\\(.*?\\)", ""));
            StateName stateName2 = new StateName(country2.replaceAll("\\(.*?\\)", ""));
            List<String[]> capdist = stateName1.capdist;
            if(stateName1 != null && stateName2 != null){
                for(String[] strs : capdist){
                    if(strs[3].equals(stateName2.stateid)){
                        stringList.add(country1);
                        stringList.add(country2);
                        stringList.add(strs[4]);
                        return stringList;
                    }
                }
            }

            return null;
        }catch(Exception e){
            return null;
        }
    }


    public void acceptUserInput(String begin, String end){
        List<List<String>> routes = CountryRoute.getRoutes(begin, end);
        for(int j = 0; j < routes.size(); j++){
            List<String> toot = new ArrayList<>();
            for(int i = 0; i < routes.get(j).size(); i++){
                toot.add(routes.get(j).get(i).replaceAll("\\(.*?\\)", "").trim());
            }
            routes.set(j, toot);
        }
        List<String> pathMin = new ArrayList<>();
        int sumPath = 999999;
        for(List<String> stringList : routes){
            int sumPathTest = 0;
            for(int i = 0; i < stringList.size() - 1; i++){
                List<String> path = findPath(stringList.get(i), stringList.get(i + 1));
                if(path == null){
                    sumPathTest = 99999;
                    continue;
                }
                sumPathTest = sumPathTest + Integer.parseInt(path.get(2));
            }
            if(sumPathTest < sumPath){
                sumPath = sumPathTest;
                pathMin = stringList;
            }
        }


        for(int i = 0; i < pathMin.size() - 1; i++){
            List<String> path = findPath(pathMin.get(i), pathMin.get(i + 1));
            if(path != null){
                System.out.println("* " + path.get(0) + " --> " + path.get(1) + " (" + path.get(2) + " km.)");
            }
        }

    }

    public static String termination(List<String[]> strList, String end){
        for(String[] str : strList){
            if(str[3].equals(end)){
                return str[1];
            }
        }
        return null;
    }


    public static void main(String[] args){
        IRoadTrip a3 = new IRoadTrip(args);
        //Question1 testing
        int distance = a3.getDistance("USF", "My House");
        int distance2 = a3.getDistance("France", "Spain");
        int distance3 = a3.getDistance("Canada", "Panama");
        System.out.println(distance);
        System.out.println(distance2);
        System.out.println(distance3);

        System.out.println();

        //Question2 Testing
        List<String> path = a3.findPath("Spain", "France");
        if(path != null){
            System.out.println(path.get(0) + " --> " + path.get(1) + " (" + path.get(2) + " km.)");
        }else{
            System.out.println("The country does not exist, or there is no path");
        }

        System.out.println();

        //Question3 Testing
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Enter the name of the first country (type EXIT to quit):");
            String country1 = sc.next();
            if(country1.equals("EXIT") || country1.equals("quit")){
                break;
            }
            if(new StateName(country1).borders == null){
                System.out.println("Invalid country name. Please enter a valid country name.");
                continue;
            }
            System.out.print("Enter the name of the second country (type EXIT to quit):");
            String country2 = sc.next();
            if(country2.equals("EXIT") || country2.equals("quit")){
                break;
            }
            if(new StateName(country2).borders == null){
                System.out.println("Invalid country name. Please enter a valid country name.");
                continue;

            }
            System.out.println("Route from " + country1 + " to " + country2 + ":");
            a3.acceptUserInput(country1, country2);
        }

    }
}


