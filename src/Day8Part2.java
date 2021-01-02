import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Day8Part2 {

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
            boolean trynext = false;
            for(int i=0; i<instructions.length; i++) {
                flipInstruction(instructions, i);
                trynext = false;
                visited.clear();
                for(int j=0; j< instructions.length; j++) {
                    if(visited.contains(j)) {
                        accumulator = 0;
                        trynext = true;
                        break;
                    }
                    if(instructions[j].getKey().equals("nop")) continue;
                    else if(instructions[j].getKey().equals("jmp")) j+=(Integer) instructions[j].getValue()-1;
                    else if(instructions[j].getKey().equals("acc")) accumulator+=(Integer)instructions[j].getValue();
                    visited.add(j);
                }
                if(!trynext) break;
                flipInstruction(instructions, i);
            }

            System.out.println("acc is " +accumulator);
        }
    }

    static void flipInstruction(Pair[] instructions, int i) {
        if(instructions[i].getKey().equals("nop")) {
            instructions[i] = new Pair("jmp", instructions[i].getValue());
        } else if(instructions[i].getKey().equals("jmp")) {
            instructions[i] = new Pair("nop", instructions[i].getValue());
        }
    }

}
