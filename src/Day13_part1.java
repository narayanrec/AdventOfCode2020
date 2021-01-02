import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day13_part1 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day13.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            int minTime = Integer.MAX_VALUE;

            int readyTime = Integer.valueOf(input[0]);

            String[] busIds = input[1].split(",");
            int selectedId = 0;
            for(String busId: busIds) {
                if(!busId.equals("x")) {
                    int id = Integer.valueOf(busId);
                    if(readyTime%id == 0) {
                        minTime = readyTime;
                        selectedId = id;
                    } else {
                        int earliestDep = (readyTime/id + 1) *id;
                        if(earliestDep < minTime) {
                            minTime = earliestDep;
                            selectedId = id;
                        }

                    }
                }
            }
            System.out.println("min is " + minTime);
            System.out.println("selctedId is " + selectedId);
            System.out.println("ans is " + (minTime-readyTime)*selectedId);

        }
    }


}
