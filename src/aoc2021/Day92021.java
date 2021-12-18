package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day92021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day9.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {
        int[][] grid = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                grid[i][j] = input[i].charAt(j) - '0';
            }
        }
        List<Integer> ans = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean isEligible = true;
                if(i-1 >=0 && grid[i-1][j] <= grid[i][j]) isEligible = false;
                else if(i+1 < rows && grid[i+1][j] <= grid[i][j]) isEligible = false;
                else if(j-1 >=0 && grid[i][j-1] <= grid[i][j]) isEligible = false;
                else if(j+1 < cols && grid[i][j+1] <= grid[i][j]) isEligible = false;

                if (isEligible) ans.add(grid[i][j]+1);
            }

        }
        int sum = 0;

        for(int num: ans) sum+=num;
        System.out.println("part1 ans is "  +sum);
    }

    private static void part2(String[] input) {

        int[][] grid = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                grid[i][j] = input[i].charAt(j) - '0';
            }
        }
        List<Integer> ans = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean isEligible = true;
                if(i-1 >=0 && grid[i-1][j] <= grid[i][j]) isEligible = false;
                else if(i+1 < rows && grid[i+1][j] <= grid[i][j]) isEligible = false;
                else if(j-1 >=0 && grid[i][j-1] <= grid[i][j]) isEligible = false;
                else if(j+1 < cols && grid[i][j+1] <= grid[i][j]) isEligible = false;

                if (isEligible) {
                    List<Integer> list = new ArrayList<>();

                    dfs(i, j, grid, list, visited);
                    ans.add(list.size());
                }
            }

        }
        ans.sort((e1, e2) -> e2 - e1);

        System.out.println("part2 ans is "  +ans.get(0)*ans.get(1)*ans.get(2));
    }

    private static void dfs(int i, int j, int[][] grid, List<Integer> list, boolean[][] visited) {
        if(grid[i][j] == 9 || visited[i][j]) return;
        list.add(grid[i][j]);
        visited[i][j] = true;
        if(i-1>= 0 && grid[i-1][j] > grid[i][j]) dfs(i-1, j, grid, list, visited);
        if(i+1< grid.length && grid[i+1][j] > grid[i][j]) dfs(i+1, j, grid, list, visited);
        if(j-1>= 0 && grid[i][j-1] > grid[i][j]) dfs(i, j-1, grid, list, visited);
        if(j+1< grid[0].length && grid[i][j+1] > grid[i][j]) dfs(i, j+1, grid, list, visited);
    }


}
