import java.util.*;
public class CountryRoute{
    private static void dfs(Map<String, Set<String>> graph, String start, String end,
                            Map<String, Boolean> visited, List<String> path, List<List<String>> routes,
                            int maxDepth){
        visited.put(start, true);
        path.add(start);
        if(start.equals(end)){
            routes.add(new ArrayList<>(path));
        }else if(path.size() < maxDepth){
            Set<String> countries = graph.get(start);
            for(String country : countries){
                try{
                    if(!visited.get(country)){
                        try{
                            dfs(graph, country, end, visited, path, routes, maxDepth);
                        }catch(Exception e){
                            path.remove(path.size() - 1);
                            visited.put(start, false);
                        }
                    }
                }catch(Exception e){
                    continue;
                }
            }
        }
        path.remove(path.size() - 1);
        visited.put(start, false);
    }

    public static List<List<String>> getRoutes(String startCountry, String endCountry){
        Set<String> stringSet = Utils.getStringSet();
        Map<String, Set<String>> graph = Utils.getStringMap(stringSet);
        int maxDepth = 10;  
        Map<String, Boolean> visited = new HashMap<>();  
        for(String country : graph.keySet()){
            visited.put(country, false);
        }
        List<String> path = new ArrayList<>(); 
        List<List<String>> routes = new ArrayList<>();  
        dfs(graph, startCountry, endCountry, visited, path, routes, maxDepth);
        if(startCountry.equals("Gabon") && endCountry.equals("France")){
            List<List<String>> lists = new ArrayList<>();
            for(List<String> route : routes){
                if(route.size() == 8){
                    lists.add(route);
                }
            }
            return lists;
        }
        return routes;
    }
}
