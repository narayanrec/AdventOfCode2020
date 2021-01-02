import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day10Part2 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day10.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            //System.out.println("input is " + Arrays.toString(input));
            int[] arr = new int[input.length];
            for(int i=0; i<input.length; i++) arr[i] = Integer.valueOf(input[i]);

            Arrays.sort(arr);
            System.out.println("sorted arr is " + Arrays.toString(arr));
            int[] newArr = new int[arr.length+1];
            newArr[0] = 0;
            for(int i=0; i<arr.length; i++) newArr[i+1] = arr[i];

            long[] dp = new long[newArr.length];
            dp[0] = 1;
            //4049565169664
            int start = 0;
            long combs = 1;
            for(int i=0; i<arr.length-2;i++){
                if(start+1 == arr[i] && start+2 == arr[i+1] && start+3 == arr[i+2]) {
                    combs*=4;
                    i+=2;
                } else if((start+1 == arr[i] && start+3 == arr[i+1])
                        ||  (start+2 == arr[i] && start+3 == arr[i+1])
                        || (start+1 == arr[i] && start+2 == arr[i+1])
                        ){
                    combs*=2;
                    i+=1;
                }
                start = arr[i];
                //System.out.println("start is " + start);
            }

            System.out.println("ans is " + combs);
        }
    }

}
