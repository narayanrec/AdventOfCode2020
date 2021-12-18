package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day112021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day11.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            //part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {
        int[][] grid = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            grid[i] = Arrays.stream(input[i].split("")).mapToInt(it -> Integer.valueOf(it)).toArray();
        }
        int ret =0;
        LinkedList<int[]> q = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < grid.length; j++) {
                for (int k = 0; k < grid[0].length; k++) {
                    grid[j][k]++;
                    if (grid[j][k] == 10) q.add(new int[]{j, k});
                }
            }
            int r = grid.length;
            int c = grid[0].length;
            int[][] mvs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

            while (!q.isEmpty()) {
                int n = q.size();
                for (int m = 0; m < n; m++) {
                    int[] e = q.poll();
                    int j = e[0];
                    int k = e[1];

                    ret++;
                    grid[j][k] = 0;
                    for (int[] mv : mvs) {
                        if (isWithinBounds(j + mv[0], k + mv[1], r, c, grid)) {
                            grid[j + mv[0]][k + mv[1]]++;
                            if (grid[j + mv[0]][k + mv[1]] == 10) q.add(new int[]{j + mv[0], k + mv[1]});
                        }
                    }
                }
            }
        }

        System.out.println("part1 ans is " +ret);
    }

    private static boolean isWithinBounds(int j, int k, int r, int c, int[][] grid) {
        return j>=0 && k >=0 && j< r && k < c && grid[j][k] < 10 && grid[j][k] != 0;
    }


    private static void part2(String[] input) {
        int[][] grid = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            grid[i] = Arrays.stream(input[i].split("")).mapToInt(it -> Integer.valueOf(it)).toArray();
        }
        int ret =0;
        LinkedList<int[]> q = new LinkedList<>();
        while (!allZeros(grid)) {
            ret++;
            for (int j = 0; j < grid.length; j++) {
                for (int k = 0; k < grid[0].length; k++) {
                    grid[j][k]++;
                    if (grid[j][k] == 10) q.add(new int[]{j, k});
                }
            }
            int r = grid.length;
            int c = grid[0].length;
            int[][] mvs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

            while (!q.isEmpty()) {
                int n = q.size();
                for (int m = 0; m < n; m++) {
                    int[] e = q.poll();
                    int j = e[0];
                    int k = e[1];

                    grid[j][k] = 0;
                    for (int[] mv : mvs) {
                        if (isWithinBounds(j + mv[0], k + mv[1], r, c, grid)) {
                            grid[j + mv[0]][k + mv[1]]++;
                            if (grid[j + mv[0]][k + mv[1]] == 10) q.add(new int[]{j + mv[0], k + mv[1]});
                        }
                    }
                }
            }
        }

        System.out.println("part2 ans is "+ret  );
    }

    private static boolean allZeros(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] != 0) return false;
            }
        }
        return true;
    }


}
