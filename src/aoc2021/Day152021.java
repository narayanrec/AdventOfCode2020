package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

public class Day152021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day15.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }
    static int ret = Integer.MAX_VALUE;
    private static void part1(String[] input) {
        int r = input.length;
        int c = input[0].length();
        int[][] grid = new int[r][c];

        for (int i = 0; i < input.length; i++) {
            grid[i] = Arrays.stream(input[i].split("")).mapToInt(it -> Integer.valueOf(it)).toArray();
        }

        dfs(0, 0, grid, 0);
        System.out.println("part1 ans is " +ret);
    }

    private static void dfs(int i, int j, int[][] grid, int sum) {
        if(i == grid.length || j == grid[0].length) return;
        if(i== grid.length-1 && j == grid[0].length-1)
            ret = Math.min(ret, sum+grid[i][j]);

        if((i+j) != 0) sum += grid[i][j];

        dfs(i+1, j, grid, sum);
        dfs(i, j+1, grid, sum);
    }


    private static void part2(String[] input) {

        System.out.println("part2 ans is ");
    }

}
