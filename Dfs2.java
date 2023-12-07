import java.util.*;
public class Dfs2{
    private Map<String, Set<String>> graph;
    public Dfs2(Map<String, Set<String>> graph){
        this.graph = graph;
    }
    public List<String> findShortestPath(String start, String target){
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        while(!queue.isEmpty()){
            String current = queue.poll();
            if(current.equals(target)){
                return buildPath(parentMap, start, target);
            }
            Set<String> neighbors = graph.get(current);
            if(neighbors != null){
                for(String neighbor : neighbors){
                    if(!visited.contains(neighbor)){
                        queue.offer(neighbor);
                        visited.add(neighbor);
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }
        return null;
    }

    private List<String> buildPath(Map<String, String> parentMap, String start, String target){
        List<String> path = new ArrayList<>();
        String current = target;
        while(current != null){
            path.add(current);
            if(current.equals(start)){
                break;
            }
            current = parentMap.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args){
        List<Borders> listBorders = DFSPathFinder.getListBorders();
        Map<String, Set<String>> graph = new HashMap<>();
        for(Borders b :listBorders){
            if(b.country!=null){
                graph.put(b.bordersName, b.country.keySet());
            }else{
                graph.put(b.bordersName, null);
            }
        }
        Dfs2 pathFinder = new Dfs2(graph);
        List<String> path = pathFinder.findShortestPath("Paraguay", "Colombia");
        if(path != null){
            System.out.println("The shortest path from node Paraguay to node Colombia is:" + path);
        }else{
            System.out.println("There is no path from node Paraguay to node Columbia");
        }
    }

}
