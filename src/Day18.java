import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Stream;

public class Day18 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day18.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            part1(input);
            //part2(input);
        }
    }

    static void part1(String[] input) {
        Stack<Long> vals = new Stack<>();
        Stack<Character> markers = new Stack<>();
        Stack<Character> orderedMarkers = new Stack<>();
        Stack<Long> orderedVals = new Stack<>();
        long sum = 0;
        for (String exp: input) {
            exp = exp.replaceAll("\\s", "");
            for (int i = 0; i < exp.length(); i++) {
                if(exp.charAt(i) != ')') {
                    if(!Character.isDigit(exp.charAt(i))) {
                        markers.add(exp.charAt(i));
                    } else {
                        vals.add(Long.valueOf(exp.charAt(i) - '0'));
                    }

                } else {
                    while(markers.peek() != '('){
                        orderedMarkers.add(markers.pop());
                        orderedVals.add(vals.pop());

                    }
                    orderedVals.add(vals.pop());
                    markers.pop();
                    evaluateOrderedExpression(orderedMarkers, orderedVals);
                    vals.add(orderedVals.pop());
                }
            }
            while(!markers.isEmpty()) {
                orderedMarkers.add(markers.pop());
                orderedVals.add(vals.pop());
            }
            orderedVals.add(vals.pop());
            evaluateOrderedExpression(orderedMarkers, orderedVals);
            sum+=orderedVals.pop();
        }

        System.out.println("sum of all expression is " +sum);
    }

    static void evaluateOrderedExpression(Stack<Character> orMarkers, Stack<Long> orVals) {
        Stack<Long> addedVals = new Stack<>();
        while (!orMarkers.isEmpty()) {
            if(orMarkers.peek() == '+')orVals.add(evaluateExpression(orMarkers.pop(), orVals.pop(), orVals.pop()));
            else {
                addedVals.add(orVals.pop());
                orMarkers.pop();
            }
        }
        addedVals.add(orVals.pop());
        while (addedVals.size() != 1) {
            addedVals.add(evaluateExpression('*', addedVals.pop(), addedVals.pop()));
        }
        orVals.add(addedVals.pop());
    }

    static long evaluateExpression(char operator, long val1, long val2) {
        if(operator == '+') {
            return val1 + val2;
        } else if(operator == '*') {
            return val1 * val2;
        }
        return 0;
    }



    static void part2(String[] input) {

        System.out.println("cubes in active state is");
    }

}
