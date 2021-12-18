package aoc2021;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day142021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day14.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            //part1(input);
            part2(input);
        }
    }
    private static void part1(String[] input) {
        String template = input[0];
        Map<String, String> map = new HashMap<>();

        for (int i = 2 ; i < input.length; i++) {
            String[] parts = input[i].split("->");
            map.put(parts[0].trim(), parts[1].trim());
        }

        for (int i = 0; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(template.charAt(0));
            for (int j = 0; j < template.length()-1; j++) {
                String pair = template.substring(j, j+2);
                sb.append(map.get(pair));
                sb.append(pair.charAt(1));
            }
            template = sb.toString();
        }

        int[] count = new int[26];

        for (int i = 0; i < template.length(); i++) {
            count[template.charAt(i)-'A']++;
        }
        int max= 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            max = Math.max(max, count[i]);
            if(count[i] != 0)
                min = Math.min(min, count[i]);
        }
        int ans = max - min;
        System.out.println("part1 ans is " + ans);
    }


    private static void part2(String[] input) {
        String template = input[0];
        Map<String, String> map = new HashMap<>();

        for (int i = 2 ; i < input.length; i++) {
            String[] parts = input[i].split("->");
            map.put(parts[0].trim(), parts[1].trim());
        }
        Map<String, Long> pairMap = new HashMap<>();
        for (int j = 0; j < template.length()-1; j++) {
            String pair = template.substring(j, j+2);
            pairMap.put(pair, pairMap.getOrDefault(pair, 0l)+1);
        }

        Map<Character, Long> chCount = new HashMap<>();
        for (int i = 0; i < template.length(); i++) {
            chCount.put(template.charAt(i), chCount.getOrDefault(template.charAt(i), 0l) +1 );
        }

        for (int i = 0; i < 40; i++) {
            Map<String, Long> newPairMap = new HashMap<>();

            for (String pair: pairMap.keySet()) {

                if(map.containsKey(pair)) {
                    long inc = pairMap.get(pair);
                    String key = map.get(pair);
                    String p1 = pair.substring(0, 1) + key;
                    String p2 = key + pair.substring(1, 2) ;
                    newPairMap.put(p1, newPairMap.getOrDefault(p1, 0l) +inc);
                    newPairMap.put(p2, newPairMap.getOrDefault(p2, 0l)+ inc);
                    chCount.put(key.charAt(0), chCount.getOrDefault(key.charAt(0), 0l)+inc);
                } else {
                    newPairMap.put(pair, pairMap.get(pair));

                }
            }
            pairMap = newPairMap;
        }


        long ans = chCount.values().stream().mapToLong(it -> it).max().getAsLong() - chCount.values().stream().mapToLong(it -> it).min().getAsLong();
        System.out.println("part2 ans is "+ ans);
    }

}
