package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day82021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day8.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {

        int count =0;

        for(String line: input) {
            String[] parts = line.split("\\|");
            String[] outputVals = parts[1].trim().split("\\s+");
            for(String val: outputVals) {
                if(val.length() == 2 || val.length() == 4 || val.length() == 3 || val.length() == 7) count++;
            }
        }
        System.out.println("part1 ans is "     +count );
    }

    private static void part2(String[] input) {
        int sum = 0;
        for(String line: input) {
            String[] parts = line.split("\\|");
            String[] vals = parts[0].trim().split("\\s+");
            Set<Character> oneSet = new HashSet<>();
            Set<Character> fourSet = new HashSet<>();
            Set<Character> sevenSet = new HashSet<>();
            for(String val: vals) {
                if(val.length() == 2 ) {
                    for (int i = 0; i < val.length(); i++) {
                        oneSet.add(val.charAt(i));
                    }
                } else if(val.length() == 4) {

                    for (int i = 0; i < val.length(); i++) {
                        if(!oneSet.contains(val.charAt(i)))
                            fourSet.add(val.charAt(i));
                    }

                    fourSet.removeAll(oneSet);
                } else if(val.length() == 3) {
                    for (int i = 0; i < val.length(); i++) {
                        if(!oneSet.contains(val.charAt(i)))
                            sevenSet.add(val.charAt(i));
                    }

                }
            }
            fourSet.removeAll(oneSet);
            sevenSet.removeAll(oneSet);
            String[] outVals = parts[1].trim().split("\\s+");
            StringBuilder sb = new StringBuilder();
            for(String outVal: outVals) {
                if(outVal.length() == 2) sb.append("1");
                else if(outVal.length() == 3) sb.append("7");
                else if(outVal.length() == 4) sb.append("4");
                else if(outVal.length() == 7) sb.append("8");
                else if(outVal.length() == 5) {
                    Set<Character> set = new HashSet<>();
                    for (int i = 0; i < outVal.length(); i++) {
                        set.add(outVal.charAt(i));
                    }
                    if(set.containsAll(oneSet)) sb.append("3");
                    else if(set.containsAll(fourSet)) sb.append("5");
                    else sb.append("2");
                } else {
                    Set<Character> set = new HashSet<>();
                    for (int i = 0; i < outVal.length(); i++) {
                        set.add(outVal.charAt(i));
                    }
                    if(!set.containsAll(oneSet)) sb.append("6");
                    else if(set.containsAll(fourSet)) sb.append("9");
                    else sb.append("0");
                }
            }
            sum+= Integer.valueOf(sb.toString());

        }
        System.out.println("part2 ans is "+sum);
    }
}
