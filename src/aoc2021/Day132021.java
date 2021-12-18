package aoc2021;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day132021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day13.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }
    private static void part1(String[] input) {
        int maxX = 0;
        int maxY = 0;
        int[][] grid = null;
        for (int i = 0; i < input.length; i++) {
            while (!input[i].equals("")) {
                String[] parts = input[i].split(",");
                maxX = Math.max(maxX, Integer.valueOf(parts[0]));
                maxY = Math.max(maxY, Integer.valueOf(parts[1]));
                i++;
            }
            grid = new int[maxY+1][maxX+1];

            i =0;
            while (!input[i].equals("")) {
                String[] parts = input[i].split(",");
                int x = Integer.valueOf(parts[0]);
                int y = Integer.valueOf(parts[1]);

                grid[y][x] = 1;
                i++;
            }
            break;
        }
        int foldX = 655;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 656; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    grid[i][foldX-(j-foldX)] = 1;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 656; j++) {
                if(grid[i][j] == 1) ret++;
            }
        }



        System.out.println("part1 ans is "+ret);
    }


    private static void part2(String[] input) {
        int maxX = 0;
        int maxY = 0;
        int[][] grid = null;
        List<Pair<Character, Integer>> folds = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            while (!input[i].equals("")) {
                String[] parts = input[i].split(",");
                maxX = Math.max(maxX, Integer.valueOf(parts[0]));
                maxY = Math.max(maxY, Integer.valueOf(parts[1]));
                i++;
            }
            grid = new int[maxY+1][maxX+1];

            i =0;
            while (!input[i].equals("")) {
                String[] parts = input[i].split(",");
                int x = Integer.valueOf(parts[0]);
                int y = Integer.valueOf(parts[1]);

                grid[y][x] = 1;
                i++;
            }
            i++;
            while (i < input.length) {
                String[] parts = input[i].split("=");
                folds.add(new Pair<>(parts[0].charAt(parts[0].length()-1), Integer.valueOf(parts[1])));
                i++;
            }
        }
        for(Pair<Character, Integer> pair: folds) {
            if(pair.getKey() == 'x') {
                int foldX = pair.getValue();

                for (int i = 0; i < grid.length; i++) {
                    for (int j = foldX+1; j < grid[0].length; j++) {
                        if(grid[i][j] == 1) {
                            grid[i][foldX-(j-foldX)] = 1;
                            grid[i][j] = 0;
                        }
                    }
                }
            }
            else {
                int foldY = pair.getValue();

                for (int i = foldY+1; i < grid.length; i++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        if(grid[i][j] == 1) {
                            grid[foldY-(i-foldY)][j] = 1;
                            grid[i][j] = 0;
                        }
                    }
                }
            }


        }



        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 41; j++) {
                if(grid[i][j] == 1)
                    System.out.print("X");
                else
                    System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("part2 ans is ");
    }

}
