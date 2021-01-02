import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day13Part2 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day13.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));

            String[] busIds = input[1].split(",");
            List<Pair<Integer, Integer>> offsets = new ArrayList<>();

            for(int i=0; i<busIds.length; i++) {
                if(!busIds[i].equals("x")) offsets.add(new Pair(Integer.valueOf(busIds[i]), i));
            }
            int startId = offsets.get(0).getKey();
            long time = 0;
            //System.out.println("earliest time is" +earliestTime);
            offsets.remove(0);
            boolean check = false;
            long increment=startId;

            for(Pair<Integer, Integer> busOffset: offsets) {
                long remainder;
                do {
                    time+=increment;
                    remainder = (time+busOffset.getValue() ) % busOffset.getKey();

                } while (remainder != 0);
                increment *= busOffset.getKey();
            }


            System.out.println("earliest time is " + time);

        }
    }


}
