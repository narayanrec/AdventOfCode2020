import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day17 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day17.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            //part1(input);
            part2(input);
        }
    }

    static void part1(String[] input) {

        char[][] arrIn = new char[input.length][input.length];
        for (int i = 0; i < input.length; i++) {
            arrIn[i] = input[i].toCharArray();
        }

        char[][][] grid = new char[input.length+20][input.length+20][input.length+20];

        for (int z = 0; z < grid.length; z++) {
            for(int y=0; y<grid[0].length; y++) {
                for(int x=0; x<grid[0][0].length; x++) {
                    grid[z][y][x] = '.';
                }
            }
        }
        int zIndex = grid.length/2 - input.length/2;
        for (int y = 0; y < arrIn.length; y++) {
            for (int x = 0; x < arrIn[0].length; x++) {
                char c = arrIn[y][x];

                grid[zIndex][zIndex+y][zIndex+x] = c;
            }
            
        }

        for(int cycle=1; cycle<=6; cycle++) {
            char[][][] copy = new char[grid.length][grid.length][grid.length];

            for(int z=0; z< grid.length; z++) {
                for (int y = 0; y < grid.length; y++) {
                    for (int x = 0; x < grid.length; x++) {
                        int activeNeighbours = countNeighbours1(grid, z, y, x);
                        if(grid[z][y][x] == '#') {
                            if(activeNeighbours == 2 || activeNeighbours ==3)
                                copy[z][y][x] = '#';
                            else
                                copy[z][y][x] = '.';
                        } else {
                            if(activeNeighbours == 3)
                                copy[z][y][x] = '#';
                            else
                                copy[z][y][x] = '.';
                        }
                    }

                }
            }

            grid = copy;
        }

        int active = 0;
        for(int z=0; z< grid.length; z++) {
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid.length; x++) {
                    if(grid[z][y][x] == '#') active++;
                }
            }
        }

        System.out.println("cubes in active state is" +active);
    }

    private static int countNeighbours1(char[][][] grid, int z, int y, int x) {
        int count =0;

        for (int zi = -1; zi <= 1; zi++) {
            for (int yi = -1; yi <=1 ; yi++) {
                for (int xi = -1; xi <=1 ; xi++) {
                    if(z+zi >=0 && y+yi >=0 && x+xi >=0) {
                        if(z+zi <grid.length && y+yi <grid.length && x+xi < grid.length) {
                            if(!(zi ==0 && yi==0 && xi ==0)) {
                                if(grid[z+zi][y+yi][x+xi] == '#') count++;
                            }
                        }
                    }
                }

            }
        }
        return count;
    }

    private static int countNeighbours2(char[][][][] grid, int w, int z, int y, int x) {
        int count =0;
        for (int wi = -1; wi <=1 ; wi++) {
            for (int zi = -1; zi <= 1; zi++) {
                for (int yi = -1; yi <=1 ; yi++) {
                    for (int xi = -1; xi <=1 ; xi++) {
                        if(w+wi >=0 && z+zi >=0 && y+yi >=0 && x+xi >=0) {
                            if(w+wi < grid.length && z+zi <grid.length && y+yi <grid.length && x+xi < grid.length) {
                                if(!(wi == 0 && zi ==0 && yi==0 && xi ==0)) {
                                    if(grid[w+wi][z+zi][y+yi][x+xi] == '#') count++;
                                }
                            }
                        }
                    }

                }
            }
        }

        return count;
    }

    static void part2(String[] input) {
        char[][] arrIn = new char[input.length][input.length];
        for (int i = 0; i < input.length; i++) {
            arrIn[i] = input[i].toCharArray();
        }

        char[][][][] grid = new char[input.length+20][input.length+20][input.length+20][input.length+20];

        for (int w = 0; w < grid.length; w++) {
            for (int z = 0; z < grid[0].length; z++) {
                for(int y=0; y<grid[0][0].length; y++) {
                    for(int x=0; x<grid[0][0][0].length; x++) {
                        grid[w][z][y][x] = '.';
                    }
                }
            }
        }

        int zIndex = grid.length/2 - input.length/2;
        int wIndex = grid.length/2 - input.length/2;
        for (int y = 0; y < arrIn.length; y++) {
            for (int x = 0; x < arrIn[0].length; x++) {
                char c = arrIn[y][x];

                grid[wIndex][zIndex][zIndex+y][zIndex+x] = c;
            }

        }

        for(int cycle=1; cycle<=6; cycle++) {
            char[][][][] copy = new char[grid.length][grid.length][grid.length][grid.length];
            for (int w = 0; w < grid.length; w++) {
                for(int z=0; z< grid.length; z++) {
                    for (int y = 0; y < grid.length; y++) {
                        for (int x = 0; x < grid.length; x++) {
                            int activeNeighbours = countNeighbours2(grid, w, z, y, x);
                            if(grid[w][z][y][x] == '#') {
                                if(activeNeighbours == 2 || activeNeighbours ==3)
                                    copy[w][z][y][x] = '#';
                                else
                                    copy[w][z][y][x] = '.';
                            } else {
                                if(activeNeighbours == 3)
                                    copy[w][z][y][x] = '#';
                                else
                                    copy[w][z][y][x] = '.';
                            }
                        }

                    }
                }
            }
            grid = copy;
        }

        int active = 0;
        for (int w = 0; w < grid.length; w++) {
            for(int z=0; z< grid.length; z++) {
                for (int y = 0; y < grid.length; y++) {
                    for (int x = 0; x < grid.length; x++) {
                        if(grid[w][z][y][x] == '#') active++;
                    }
                }
            }
        }


        System.out.println("cubes in active state is" +active);
    }

}
