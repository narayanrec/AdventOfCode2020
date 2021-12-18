package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day122021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day12.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            //part1(input);
            part2(input);
        }
    }
    static int ans = 0;
    private static void part1(String[] input) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> visited = new HashSet<>();
        for(String conn: input) {
            String[] parts = conn.split("-");
            map.putIfAbsent(parts[0], new ArrayList<>());
            map.putIfAbsent(parts[1], new ArrayList<>());
            map.get(parts[0]).add(parts[1]);
            map.get(parts[1]).add(parts[0]);
        }
        visited.add("start");
        dfs("start", map, visited);



        System.out.println("part1 ans is " +ans);
    }

    static void dfs(String fromNode,  Map<String, List<String>> map, Set<String> visited) {
        if(fromNode.equals("end")) {
            ans++;
            return;
        }

        for(String toNode: map.get(fromNode)) {
            if(visited.contains(toNode)) continue;
            if(isSmallCave(toNode))visited.add(toNode);
            dfs(toNode, map, visited);
            visited.remove(toNode);
        }
    }

    private static boolean isSmallCave(String part) {
        for (int i = 0; i < part.length(); i++) {
            if(part.charAt(i) - 'A' < 26 ) return false;
        }
        return true;
    }

    private static void part2(String[] input) {
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> visited = new HashMap<>();
        for(String conn: input) {
            String[] parts = conn.split("-");
            map.putIfAbsent(parts[0], new ArrayList<>());
            map.putIfAbsent(parts[1], new ArrayList<>());
            map.get(parts[0]).add(parts[1]);
            map.get(parts[1]).add(parts[0]);
        }
        visited.put("start", 1);
        dfs1("start", map, visited);


        System.out.println("part2 ans is "  +ans);
    }

    static void dfs1(String fromNode,  Map<String, List<String>> map, Map<String, Integer> visited) {
        if(fromNode.equals("end")) {
            ans++;
            return;
        }

        for(String toNode: map.get(fromNode)) {
            if(isSmallCave(toNode) && visitedContainsDoubles(visited)
                    && visited.containsKey(toNode)
                    && (visited.get(toNode) == 1 || visited.get(toNode) == 2)) continue;
            if(toNode.equals("start")) continue;
            if(isSmallCave(toNode)) {
                visited.put(toNode, visited.getOrDefault(toNode, 0) +1);
            }

            dfs1(toNode, map, visited);

            if(isSmallCave(toNode))visited.put(toNode, visited.get(toNode)-1);
        }
    }

    static boolean visitedContainsDoubles(Map<String, Integer> visitedMap) {
        for(String key: visitedMap.keySet()) {
            if(visitedMap.get(key) == 2) return true;
        }
        return false;
    }


}
