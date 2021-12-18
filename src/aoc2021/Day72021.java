package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day72021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day7.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {
        List<Integer> state = Arrays.stream(input[0].split(",")).map(it -> Integer.valueOf(it)).collect(Collectors.toList());
        state.sort(Comparator.naturalOrder());
        int n = state.size();
        int minPos = state.get(0);
        int maxPos = state.get(n-1);
        int ans = Integer.MAX_VALUE;
        for (int i = minPos; i < maxPos; i++) {
            int sum = 0;
            for(int pos: state) {
                sum += Math.abs(i-pos);
            }
            ans = Math.min(ans, sum);
        }
        System.out.println("part1 ans is "+ans);
    }

    private static void part2(String[] input) {

        List<Integer> state = Arrays.stream(input[0].split(",")).map(it -> Integer.valueOf(it)).collect(Collectors.toList());
        state.sort(Comparator.naturalOrder());
        int n = state.size();
        int minPos = state.get(0);
        int maxPos = state.get(n-1);
        int ans = Integer.MAX_VALUE;
        for (int i = minPos; i < maxPos; i++) {
            int sum = 0;
            for(int pos: state) {
                int move = Math.abs(i-pos);
                int fuelCost = (move*(move+1))/2;
                sum += fuelCost;
            }
            ans = Math.min(ans, sum);
        }
        System.out.println("part2 ans is "+ans);
    }
}
