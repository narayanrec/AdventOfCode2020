package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day32021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day3.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {
        String gammaRate = "";
        String epsilonRate = "";
        int[] freq = new int[input[0].length()];
        int n = input.length;
        for(String val : input) {
            for(int i=0; i<val.length(); i++) {
                if(val.charAt(i) == '1') freq[i]++;
            }
        }

        for(int f: freq) {
            if(f > n/2) {
                gammaRate = gammaRate + "1";
                epsilonRate = epsilonRate + "0";
            } else {
                gammaRate = gammaRate + "0";
                epsilonRate = epsilonRate + "1";
            }
        }

        int ga = Integer.valueOf(gammaRate, 2);
        int ep = Integer.valueOf(epsilonRate, 2);

        System.out.println("ans is " + ga*ep);
    }

    private static void part2(String[] input) {
        List<String> q = new ArrayList<>();
        for(String in: input) q.add(in);

        String num = processQ(q, '1', '0');

        int oxygen = Integer.valueOf(num, 2);

        num = processQ(q, '0', '1');
        int carbon = Integer.valueOf(num, 2);
        System.out.println("oxygen is " + oxygen);
        System.out.println("carbon is " + carbon);
        System.out.println("ans is " + oxygen*carbon);
    }

    private static String processQ(List<String> q, char first, char second) {
        int pos=0;
        while(q.size() > 1) {
            int size = q.size();
            int count = 0;
            for(int i=0; i< size; i++) {
                String val = q.get(i);
                if(val.charAt(pos) == '1') count++;
            }

            int p = pos;
            if(size%2 == 0 && count == size/2) {
                q = q.stream().filter(it -> it.charAt(p) == first).collect(Collectors.toList());
            } else if(count > size/2) {
                q = q.stream().filter(it -> it.charAt(p) == first).collect(Collectors.toList());
            } else {
                q = q.stream().filter(it -> it.charAt(p) == second).collect(Collectors.toList());
            }
            pos++;
        }
        return q.get(0);
    }
}
