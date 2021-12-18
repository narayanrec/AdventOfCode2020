package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day42021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day4.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            //part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {
        List<Integer> pickedNums = Arrays.stream(input[0].split(",")).map(it -> Integer.valueOf(it)).collect(Collectors.toList());

        List<int[][]> boards = new ArrayList<>();
        for(int i=1; i<input.length;i++) {
            if(input[i].equals("")) continue;
            int[][] board = new int[5][5];
            int j = 0;
            while (i< input.length && !input[i].equals("")) {
                board[j++] = Arrays.stream(input[i].split("\\s")).filter(it -> !it.equals("")).mapToInt(it -> Integer.parseInt(it)).toArray();
                i++;
            }
            boards.add(board);
        }

        for (int i = 0; i < pickedNums.size(); i++) {
            for (int j = 0; j < boards.size(); j++) {
                int[][] board = boards.get(j);
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if(board[k][l] == pickedNums.get(i))
                            board[k][l] = -1;
                        if(checkWinningBoard(board, k, l)) {
                            int sum = 0;

                            for (int m = 0; m < 5; m++) {
                                for (int n = 0; n < 5; n++) {
                                    if(board[m][n] != -1)
                                        sum += board[m][n];
                                }
                            }
                            sum*= pickedNums.get(i);
                            System.out.println("part1 ans is "+ sum);
                            System.exit(0);
                        }
                    }

                }
            }
        }
    }

    private static boolean checkWinningBoard(int[][] board, int k, int l) {
        boolean allRowElemChecked = true;
        boolean allColElemChecked = true;
        for (int i = 0; i < 5; i++) {
            if(board[k][i] != -1) allRowElemChecked =false;
        }

        for (int i = 0; i < 5; i++) {
            if(board[i][l] != -1) allColElemChecked = false;

        }

        return allRowElemChecked || allColElemChecked;
    }


    private static void part2(String[] input) {
        List<Integer> pickedNums = Arrays.stream(input[0].split(",")).map(it -> Integer.valueOf(it)).collect(Collectors.toList());

        List<int[][]> boards = new ArrayList<>();
        for(int i=1; i<input.length;i++) {
            if(input[i].equals("")) continue;
            int[][] board = new int[5][5];
            int j = 0;
            while (i< input.length && !input[i].equals("")) {
                board[j++] = Arrays.stream(input[i].split("\\s")).filter(it -> !it.equals("")).mapToInt(it -> Integer.parseInt(it)).toArray();
                i++;
            }
            boards.add(board);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < pickedNums.size(); i++) {
            for (int j = 0; j < boards.size(); j++) {
                int[][] board = boards.get(j);
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if(board[k][l] == pickedNums.get(i))
                            board[k][l] = -1;
                        if(checkWinningBoard(board, k, l)) {
                            set.add(j);
                            if(set.size()==boards.size()) {
                                int sum = 0;

                                for (int m = 0; m < 5; m++) {
                                    for (int n = 0; n < 5; n++) {
                                        if(board[m][n] != -1)
                                            sum += board[m][n];
                                    }
                                }
                                sum*= pickedNums.get(i);
                                System.out.println("part2 ans is "+ sum);
                                System.exit(0);
                            }

                        }
                    }

                }
            }
        }
    }
}
