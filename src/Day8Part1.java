
import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day8Part1 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day8.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            Pair[] instructions = new Pair[input.length];
            for(int i=0; i<input.length; i++) {
                String[] parts = input[i].split("\\s");
                Pair<String, Integer> pair = new Pair(parts[0], Integer.valueOf(parts[1]));
                instructions[i] = pair;
            }
            Set<Integer> visited = new HashSet<>();
            int accumulator = 0;
            for(int i=0; i<instructions.length; i++) {
                if(visited.contains(i)) break;

                if(instructions[i].getKey().equals("nop")) continue;
                else if(instructions[i].getKey().equals("jmp")) i+=(Integer) instructions[i].getValue()-1;
                else if(instructions[i].getKey().equals("acc")) accumulator+=(Integer)instructions[i].getValue();
                visited.add(i);
            }

            System.out.println("acc is " +accumulator);
        }
    }

}
