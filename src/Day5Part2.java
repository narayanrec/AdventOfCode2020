import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day5Part2 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day5.txt";

        try(Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[] :: new);
            //System.out.println("input is " + Arrays.toString(input));
            List<Integer> seatIds = new ArrayList<>();

            for(String fullCode : input) {

                String newStr = fullCode.replaceAll("[FL]", "0").replaceAll("[BR]", "1");

                int seatId = Integer.valueOf(newStr, 2);
                seatIds.add(seatId);
            }
            seatIds.sort(Comparator.naturalOrder());
            for(int i=0; i< seatIds.size(); i++) {
                if(seatIds.get(i)-61 != i) {
                    System.out.println("my seat id is " + (i+61));
                    break;
                }
            }
        }
    }

}
