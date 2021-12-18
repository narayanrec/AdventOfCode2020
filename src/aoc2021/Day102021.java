package aoc2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day102021 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//aoc2021//input_day10.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            part2(input);
        }
    }

    private static void part1(String[] input) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        map.put('<', '>');
        List<Character> invalidChars = new ArrayList<>();
        int sum =0;
        for(String in: input) {
            stack.clear();

            for (int i = 0; i < in.length(); i++) {
                if(map.containsKey(in.charAt(i)))
                    stack.add(in.charAt(i));
                else {
                    if(!stack.isEmpty() && map.get(stack.peek()) == in.charAt(i))
                        stack.pop();
                    else {
                        stack.pop();
                        if(in.charAt(i) == ')') sum +=3;
                        else if(in.charAt(i) == ']') sum += 57;
                        else if(in.charAt(i) == '}') sum += 1197;
                        else sum += 25137;
                    }
                }

            }
        }
        System.out.println("part1 ans is " +sum);
    }

    private static void part2(String[] input) {

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        map.put('<', '>');

        List<Long> list = new ArrayList<>();
        for(String in: input) {
            stack.clear();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                if(map.containsKey(in.charAt(i)))
                    stack.add(in.charAt(i));
                else {
                    if(!stack.isEmpty() && map.get(stack.peek()) == in.charAt(i))
                        stack.pop();
                    else {
                        stack.clear();
                        break;
                    }
                }

            }
            if(stack.isEmpty()) continue;
            while (!stack.isEmpty()) sb.append(map.get(stack.pop()));

            long sum = 0;
            String str = sb.toString();
            for (int i = 0; i < str.length(); i++) {
                sum = sum*5;
                if(str.charAt(i) == ')')
                    sum+=1;
                else if(str.charAt(i) == ']') sum += 2;
                else if(str.charAt(i) == '}') sum +=3;
                else sum += 4;
            }
            list.add(sum);

        }
        list.sort(Comparator.naturalOrder());

        System.out.println("part2 ans is "  +list.get(list.size()/2));
    }



}
