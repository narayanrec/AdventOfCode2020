import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day7Part1 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day7.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            Map<String, List<String>> graph = new HashMap<>();
            String searchKey = "shiny.gold";
            for(int i=0; i<input.length; i++) {
                String[] parts = input[i].split("\\s+");
                String key = parts[0]+"."+parts[1];
                graph.putIfAbsent(key, new ArrayList<String>());
                if(!parts[4].equals("no"))
                    graph.get(key).add(parts[5]+"."+parts[6]);

                int j= 9;
                while(parts.length > j) {
                    //System.out.println("j " +j);
                    graph.get(key).add(parts[j]+"."+parts[j+1]);
                    j+=4;
                }
            }
            Queue<String> q = new LinkedList<>();
            int count =0;
            for(String key : graph.keySet()) {
                System.out.println("key is " +key);
                if(key.equals(searchKey)) continue;
                q.add(key);

                while(!q.isEmpty()) {
                    String color = q.poll();
                    if(color.equals(searchKey)) {
                        count++;
                        break;
                    }
                    if(graph.get(color) == null) continue;
                    System.out.println("childs are " +graph.get(color).toString());
                    for(String child: graph.get(color)) q.add(child);

                }
                while(!q.isEmpty()) q.poll();
            }

            System.out.println("count is " +count);
        }
    }

}
