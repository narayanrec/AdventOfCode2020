import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day5Part1 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day5.txt";

        try(Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[] :: new);
            System.out.println("input is " + Arrays.toString(input));
            int maxSeatId = 0;
            for(String fullCode : input) {
                int row = binarySearch(0, 127, fullCode.substring(0, 7), 'F', 'B');
                int col = binarySearch(0, 7, fullCode.substring(7), 'L', 'R');
                maxSeatId = Math.max(maxSeatId, row*8+col);
            }
            System.out.println("max seat id is " + maxSeatId);
        }
    }

    static int binarySearch(int start, int end, String code, char left, char right) {
        int i=0;
        System.out.println("code is " + code);
        while(start < end) {
            int mid = start + (end-start)/2;
            System.out.println("mid is " + mid);

            if(code.charAt(i) == left) end = mid-1;
            else if(code.charAt(i) == right) start = mid +1;
            i++;
        }
        return start;
    }
}
