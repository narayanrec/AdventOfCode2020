import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day10_part1 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day10.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            int[] arr = new int[input.length];
            for(int i=0; i<input.length; i++) arr[i] = Integer.valueOf(input[i]);

            Arrays.sort(arr);
            System.out.println("sorted arr is " + Arrays.toString(arr));
            int[] res = new int[3];
            int start = 0;
            for(int i=0; i<arr.length;i++){
                if(start+1 == arr[i]) res[0]++;
                else if(start+2 == arr[i]) res[1]++;
                else if(start+3 == arr[i]) res[2]++;

                start = arr[i];
            }
            res[2]++;
            System.out.println("res is " + Arrays.toString(res));
            System.out.println("ans is " + (res[0]*res[2]));
        }
    }

}
