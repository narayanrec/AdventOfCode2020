package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day172021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day15.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {

        System.out.println("part1 ans is ");
    }


    private static void part2(String[] input) {
        int maxXVel = 76;
        int minXVel = (int) Math.ceil((-1 + Math.sqrt(1 + 8 * 56)) / 2);
        int maxYVel = 161;
        int minYVel = -162;

        int ret = 0;
        for (int i = minXVel; i <=maxXVel ; i++) {
            for (int j = minYVel; j <= maxYVel ; j++) {
                if(runSim(i, j)) ret++;
            }
        }

        System.out.println("part2 ans is " + ret);
    }

    private static boolean runSim(int i, int j) {
        int x = 0, y=0;
        while (x <= 76 && y >= -162) {
            x += i;
            y += j;
            if(i>0) i--;
            j--;
            if(x>= 56 && x<= 76 && y >= -162 && y<=-134) return true;

        }
        return false;
    }

}
