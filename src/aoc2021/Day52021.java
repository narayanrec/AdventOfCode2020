package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day52021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day5.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {
        int[][] grid = new int[1000][1000];

        for(String in: input) {
            String[] parts = in.split("->");
            String[] startCoor = parts[0].trim().split(",");
            String[] endCoor = parts[1].trim().split(",");
            int x1 = Integer.valueOf(startCoor[0]);
            int y1 = Integer.valueOf(startCoor[1]);
            int x2 = Integer.valueOf(endCoor[0]);
            int y2 = Integer.valueOf(endCoor[1]);

            if(x1== x2) {
                int i = Math.min(y1, y2);
                int limit = Math.max(y1, y2);
                while(i <= limit) {
                    grid[i][x1] += 1;
                    i++;
                }
            }

            if(y1 == y2) {
                int i = Math.min(x1, x2);
                int limit = Math.max(x1, x2);
                while(i <= limit) {
                    grid[y1][i] += 1;
                    i++;
                }
            }
        }
        int ans = 0;
        for(int i=0; i< grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] >= 2) ans++;
            }
        }
        System.out.println("part1 ans is " +ans);
    }

    private static void part2(String[] input) {
        int[][] grid = new int[1000][1000];

        for(String in: input) {
            String[] parts = in.split("->");
            String[] startCoor = parts[0].trim().split(",");
            String[] endCoor = parts[1].trim().split(",");
            int x1 = Integer.valueOf(startCoor[0]);
            int y1 = Integer.valueOf(startCoor[1]);
            int x2 = Integer.valueOf(endCoor[0]);
            int y2 = Integer.valueOf(endCoor[1]);

            if(x1== x2) {
                int i = Math.min(y1, y2);
                int limit = Math.max(y1, y2);
                while(i <= limit) {
                    grid[i][x1] += 1;
                    i++;
                }
            } else if(y1 == y2) {
                int i = Math.min(x1, x2);
                int limit = Math.max(x1, x2);
                while(i <= limit) {
                    grid[y1][i] += 1;
                    i++;
                }
            } else {
                int m = (y2-y1)/(x2-x1);
                int c = y1 - m*x1;

                int limitx = Math.max(x1, x2);


                for (int x = Math.min(x1, x2); x <= limitx; x++) {
                    int y = m*x + c;
                    grid[y][x] +=1;
                }
            }
        }
        int ans = 0;
        for(int i=0; i< grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] >= 2) ans++;
            }
        }
        System.out.println("part2 ans is " +ans);
    }
}
