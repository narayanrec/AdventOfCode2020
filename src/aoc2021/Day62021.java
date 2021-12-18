package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day62021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day6.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {
        List<Integer> state = Arrays.stream(input[0].split(",")).map(it -> Integer.valueOf(it)).collect(Collectors.toList());

        for (int i = 0; i < 80; i++) {
            int n = state.size();
            for (int j = 0; j < n; j++) {
                if(state.get(j) == 0) {
                    state.set(j, 6);
                    state.add(8);
                } else {
                    state.set(j, state.get(j)-1);
                }

            }

        }
        System.out.println("part1 ans is " +state.size());
    }

    private static void part2(String[] input) {
        List<Integer> state = Arrays.stream(input[0].split(",")).map(it -> Integer.valueOf(it)).collect(Collectors.toList());
        long[] remainingDays = new long[9];
        for(int val: state) remainingDays[val]++;

        for (int i = 0; i < 256; i++) {
            long newFish = remainingDays[0];
            long[] temp = new long[9];

            for(int j=1; j<9; j++) {
                temp[j-1] = remainingDays[j];
            }
            temp[8] = newFish;
            temp[6] += newFish;
            remainingDays = temp;

        }
        System.out.println("part2 ans is "+Arrays.stream(remainingDays).sum());
    }
}
