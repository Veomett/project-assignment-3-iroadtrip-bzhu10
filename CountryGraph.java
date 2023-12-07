import java.util.*;
public class CountryGraph{
    private Map<String, Set<String>> graph;
    public CountryGraph(Map<String, Set<String>> graph){
        this.graph = graph;
    }

    public List<List<String>> findPaths(String startCountry, String endCountry){
        List<List<String>> paths = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        List<String> currentPath = new ArrayList<>();
        currentPath.add(startCountry);
        visited.add(startCountry);
        dfs(startCountry, endCountry, graph, visited, currentPath, paths);
        return paths;
    }

    private void dfs(String country, String endCountry, Map<String, Set<String>> graph, Set<String> visited, List<String> currentPath, List<List<String>> paths){
        if(country.equals(endCountry)){
            paths.add(new ArrayList<>(currentPath));
            return;
        }
        for(String neighbor : graph.get(country)){
            if(!visited.contains(neighbor)){
                visited.add(neighbor);
                currentPath.add(neighbor);
                try{
                    dfs(neighbor, endCountry, graph, visited, currentPath, paths);
                }catch(Exception e){
                    currentPath.remove(currentPath.size() - 1);
                    visited.remove(neighbor);
                }
                currentPath.remove(currentPath.size() - 1);
                visited.remove(neighbor);
            }
        }
    }

    public static void main(String[] args){
        Set<String> stringSet = Utils.getStringSet();
        Map<String, Set<String>> graph = Utils.getStringMap(stringSet);
        CountryGraph countryGraph = new CountryGraph(graph);
        List<List<String>> paths = countryGraph.findPaths("Yemen", "Jordan");
        for(List<String> path : paths){
            System.out.println(path);
        }
    }
}
