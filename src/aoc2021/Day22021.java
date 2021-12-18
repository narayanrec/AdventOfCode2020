package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day22021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day2.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {
        int hor = 0;
        int depth= 0;

        for(String command: input) {
            String[] parts = command.split("\\s");
            if(parts[0].equals("forward")) hor += Integer.valueOf(parts[1]);
            else if(parts[0].equals("down")) depth += Integer.valueOf(parts[1]);
            else depth -= Integer.valueOf(parts[1]);
        }

        System.out.println("ans is " + hor*depth);
    }

    private static void part2(String[] input) {
        int hor = 0;
        int depth= 0;
        int aim=0;

        for(String command: input) {
            String[] parts = command.split("\\s");
            if(parts[0].equals("forward")) {
                hor += Integer.valueOf(parts[1]);
                depth += aim*Integer.valueOf(parts[1]);
            }
            else if(parts[0].equals("down")) aim += Integer.valueOf(parts[1]);
            else aim -= Integer.valueOf(parts[1]);
        }

        System.out.println("ans is " + hor*depth);
    }
}
