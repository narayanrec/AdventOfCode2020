import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day6Part2 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day6.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            int count = 0;
            for(int i=0; i<input.length; i++) {
                Map<Character, Integer> map = new HashMap<>();
                int persons = 0;

                while(i < input.length && !input[i].trim().equals("")) {
                    char[] chs = input[i].toCharArray();
                    for(Character ch: chs ) {
                        map.put(ch, map.getOrDefault(ch, 0)+1);
                    }
                    i++;
                    persons++;
                }
                for(Character ch: map.keySet()){
                    if(map.get(ch) == persons) count++;
                }
            }

            System.out.println("count is " + count);
        }
    }
}
