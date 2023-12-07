import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class DFSPathFinder{
    private Map<String, Set<String>> graph;
    private Set<String> visited;
    private List<String> path;
    public DFSPathFinder(Map<String, Set<String>> graph){
        this.graph = graph;
        visited = new HashSet<>();
        path = new ArrayList<>();
    }

    public List<String> findPath(String start, String target){
        visited.add(start);
        path.add(start);
        if(start.equals(target)){
            return path;
        }
        Set<String> neighbors = graph.get(start);
        if(neighbors != null){
            for(String neighbor : neighbors){
                if(!visited.contains(neighbor)){
                    List<String> newPath = findPath(neighbor, target);
                    if(newPath != null){
                        return newPath;
                    }
                }
            }
        }
        path.remove(path.size() - 1);
        return null;
    }

    public static void main(String[] args){
        List<Borders> listBorders = getListBorders();
        Map<String, Set<String>> graph = new HashMap<>();
        for(Borders b : listBorders){
            if(b.country != null){
                graph.put(b.bordersName, b.country.keySet());
            }else{
                graph.put(b.bordersName, new HashSet<>());
            }
        }
        DFSPathFinder pathFinder = new DFSPathFinder(graph);
        List<String> path = pathFinder.findPath("Gabon", "France");
        if(path != null){
            System.out.println("The path from node Paraguay to node Colombia is:" + path);
        }else{
            System.out.println("There is no path from node Paraguay to node Columbia");
        }

    }

    public static List<Borders> getListBorders(){
        List<Borders> bordersList = new ArrayList<>();
        String pathname = "file/borders.txt";
        try(FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            Map<String, String> map = new HashMap<>();
            one:
            while((line = br.readLine()) != null){
                String[] split = line.split(" = ");
                try{
                    bordersList.add(new Borders(split[0]));
                }catch(Exception e){
                    continue;
                }
            }
            return bordersList;
        }catch(Exception e){
            throw new RuntimeException("...");
        }
    }

}
