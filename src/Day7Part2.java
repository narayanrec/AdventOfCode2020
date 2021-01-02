
import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day7Part2 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day7.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            Map<String, List<Pair<String, Integer>>> graph = new HashMap<>();
            String searchKey = "shiny.gold";
            for(int i=0; i<input.length; i++) {
                String[] parts = input[i].split("\\s+");
                String key = parts[0]+"."+parts[1];
                graph.putIfAbsent(key, new ArrayList<>());
                if(!parts[4].equals("no"))
                    graph.get(key).add(new Pair(parts[5]+"."+parts[6], Integer.valueOf(parts[4])));

                int j= 9;
                while(parts.length > j) {
                    //System.out.println("j " +j);
                    graph.get(key).add(new Pair(parts[j]+"."+parts[j+1], Integer.valueOf(parts[j-1])));
                    j+=4;
                }
            }
            Queue<Pair<String, Integer>> q = new LinkedList<>();
            int count =0;


                q.add(new Pair<>("shiny.gold", 1));

                while(!q.isEmpty()) {
                    Pair<String, Integer> pair = q.poll();
                    System.out.println("parent is " +pair.toString());

                    count += pair.getValue();
                    if(graph.get(pair.getKey()) == null) continue;
                    System.out.println("childs are " +graph.get(pair.getKey()).toString());
                    for(Pair<String, Integer> child: graph.get(pair.getKey())) {
                        for(int i=0; i<pair.getValue(); i++) q.add(child);
                    }

                }


            System.out.println("count is " +count);
        }
    }

}
