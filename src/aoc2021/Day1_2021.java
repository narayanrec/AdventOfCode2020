package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day1_2021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day1.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            Integer[] input = stream.map(it -> Integer.valueOf(it)).toArray(Integer[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }

    private static void part1(Integer[] input) {
        int ret = 0;
        for(int i=1; i<input.length; i++) {
            if(input[i] > input[i-1]) ret++;
        }

        System.out.println("ans is " +ret);
    }

    private static void part2(Integer[] input) {
        int ret = 0;
        int prevSum = 0;
        int sum = 0;
        for(int i=0 , j=0; j<input.length;) {
            sum+=input[j];
            if(j-i == 2) {
                i++;
                j = i;
                if(prevSum != 0 && sum > prevSum) ret++;
                prevSum = sum;
                sum = 0;
            } else j++;
        }

        System.out.println("ans is " +ret);
    }

}