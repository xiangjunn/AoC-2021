package Day12.SecondPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day12/input.txt");
            BufferedReader br = new BufferedReader(fr);
            HashMap<String, List<String>> graph = new HashMap<>();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] arr = line.split("-");
                String node1 = arr[0];
                String node2 = arr[1];
                if (!graph.containsKey(node1)) {
                    List<String> adjacencyList = new ArrayList<>();
                    graph.put(node1, adjacencyList);
                }
                if (!graph.containsKey(node2)) {
                    List<String> adjacencyList = new ArrayList<>();
                    graph.put(node2, adjacencyList);
                }
                graph.get(node1).add(node2);
                graph.get(node2).add(node1);
            }
            int count =countDistinctPaths(graph, "start", new HashSet<String>(), false);
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countDistinctPaths(HashMap<String, List<String>> graph, String cave, Set<String> visited,
            boolean isVisitedTwice) {
        List<String> list = graph.get(cave);
        if (list == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            String next = list.get(i);
            if (next.equals("start")) {
                continue;
            }
            if (next.equals("end")) {
                count++;
                continue;
            }
            if (visited.contains(next) && next.equals(next.toLowerCase())) {
                if (!isVisitedTwice) {
                    count += countDistinctPaths(graph, next, visited, true);
                }
                continue;
            }
            Set<String> cloneVisited = new HashSet<>(visited);
            cloneVisited.add(next);
            count += countDistinctPaths(graph, next, cloneVisited, isVisitedTwice);
        }
        return count;
    }
}
