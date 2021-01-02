import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Day6Part1 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day6.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            int count = 0;
            for(int i=0; i<input.length; i++) {
                Set<Character> set = new HashSet<>();
                while(i < input.length && !input[i].trim().equals("")) {
                    char[] chs = input[i].toCharArray();
                    for(Character ch: chs ) {
                        set.add(ch);
                    }
                    i++;
                }
                count+=set.size();
            }

            System.out.println("count is " + count);
        }
    }
}
