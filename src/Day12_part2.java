import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Day12_part2 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day12.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));

            int x=0, y=0;
            int wx=10, wy=1;

            for(String ins : input) {
                char code = ins.charAt(0);
                int val = Integer.valueOf(ins.substring(1));
                if(code == 'F') {
                    x+= wx*val;
                    y+= wy*val;
                } else if(code == 'N') {
                    wy+=val;
                } else if(code == 'S') {
                    wy-=val;
                }else if(code == 'W') {
                    wx-=val;
                } else if(code == 'E') {
                    wx+=val;
                } else if(code == 'R') {
                    if(val/90 == 1) {
                        int temp = wx;
                        wx = wy;
                        wy = -temp;
                    } else if(val/90 == 2) {
                        wx = -wx;
                        wy = -wy;
                    } else if(val/90 == 3) {
                        int temp = wx;
                        wx = -wy;
                        wy = temp;
                    }
                } else if(code == 'L') {
                    if(val/90 == 3) {
                        int temp = wx;
                        wx = wy;
                        wy = -temp;
                    } else if(val/90 == 2) {
                        wx = -wx;
                        wy = -wy;
                    } else if(val/90 == 1) {
                        int temp = wx;
                        wx = -wy;
                        wy = temp;
                    }
                }
            }
            int res = Math.abs(x) + Math.abs(y);

            System.out.println("manhattan dist is "+ res);

        }


    }

    //90


}
