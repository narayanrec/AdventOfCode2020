import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Day24 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day24.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }


    }

    static void part1(String[] input) {
        int[][] floor = new int[50][50];
        List<List<String>> dirs = new ArrayList<>();
        for (String in: input) {
            List<String> instructions = new ArrayList<>();
            for (int i = 0; i < in.length(); i++) {
                char ch = in.charAt(i);
                String dir = "" + ch;
                if(ch == 's' || ch == 'n') {
                   dir += in.charAt(++i);
                }
                instructions.add(dir);
            }
            dirs.add(instructions);
        }


        for(List<String> ins : dirs) {
            int k=0;
            int i = 24;
            int j=24;
            while( k<ins.size()) {
                if(ins.get(k).equals("e")) j++;
                else if(ins.get(k).equals("w")) j--;
                else if(ins.get(k).equals("se")) {
                    i++;
                    j++;
                } else if(ins.get(k).equals("sw")) {
                    i++;
                } else if(ins.get(k).equals("ne")) {
                    i--;
                } else if(ins.get(k).equals("nw")) {
                    i--;
                    j--;
                }
                k++;
            }
            floor[i][j] = floor[i][j] == 0 ? 1 : 0;
        }
        //nw, w, sw, e, e
        //24, 24
        //23, 23
        //23, 22
        //24, 22
        //22, 24
        //23, 24
        int count = 0;
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[0].length; j++) {
                if(floor[i][j] == 1) count++;
            }
        }
        System.out.println("ans is " +count);
    }

    static void part2(String[] input) {
        int[][] floor = new int[200][200];
        List<List<String>> dirs = new ArrayList<>();
        for (String in: input) {
            List<String> instructions = new ArrayList<>();
            for (int i = 0; i < in.length(); i++) {
                char ch = in.charAt(i);
                String dir = "" + ch;
                if(ch == 's' || ch == 'n') {
                    dir += in.charAt(++i);
                }
                instructions.add(dir);
            }
            dirs.add(instructions);
        }


        for(List<String> ins : dirs) {
            int k=0;
            int i = 99;
            int j=99;
            while( k<ins.size()) {
                if(ins.get(k).equals("e")) j++;
                else if(ins.get(k).equals("w")) j--;
                else if(ins.get(k).equals("se")) {
                    i++;
                    j++;
                } else if(ins.get(k).equals("sw")) {
                    i++;
                } else if(ins.get(k).equals("ne")) {
                    i--;
                } else if(ins.get(k).equals("nw")) {
                    i--;
                    j--;
                }
                k++;
            }
            floor[i][j] = floor[i][j] == 0 ? 1 : 0;
        }

        for (int i = 1; i <= 100; i++) {
            int[][] copy = new int[200][200];
            copyArray(floor, copy);

            for (int j = 0; j < floor.length; j++) {
                for (int k = 0; k < floor[0].length; k++) {
                    int blackNeighbours = findBlackNeighbours(floor, j, k, 200);
                    if(floor[j][k] == 1 && (blackNeighbours==0 || blackNeighbours > 2)) {
                        copy[j][k] = 0;
                    } else if(floor[j][k] == 0 && blackNeighbours==2) {
                        copy[j][k] = 1;
                    }
                }
            }
            floor = copy;

        }

        int count = 0;
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[0].length; j++) {
                if(floor[i][j] == 1) count++;
            }
        }

        System.out.println("ans is "+count);
    }

    static void copyArray(int[][] src, int[][] target) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[0].length; j++) {
                target[i][j] = src[i][j];
            }
        }
    }

    static int findBlackNeighbours(int[][] floor, int i, int j, int n) {
        int count = 0;
        if(j+1 < n && floor[i][j+1] == 1) count++;
        if(j-1 >=0 && floor[i][j-1] == 1) count++;
        if(i+1 < n && j+1 < n && floor[i+1][j+1] == 1) count++;
        if(i+1 < n && floor[i+1][j] ==1) count++;
        if(i-1 >=0 && floor[i-1][j] == 1) count++;
        if(i-1 >= 0 && j-1 >=0 && floor[i-1][j-1] == 1) count++;

        return count;
    }

}
