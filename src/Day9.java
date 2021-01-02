import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day9 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day9.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));

            Queue<Long> q = new LinkedList<>();
            long[] arr = new long[input.length];
            long badNum = -1;
            for(int i=0; i<input.length; i++) {
                arr[i] = Long.valueOf(input[i]);
                if(i<25) q.offer(Long.valueOf(input[i]));
                else {
                    long target = Long.valueOf(input[i]);
                    if(!twoSum(q, target))  {
                        badNum = target;
                        break;
                    }
                    q.poll();
                    q.offer(target);
                }
            }
            System.out.println("bad num is " +badNum);

            long[] res = getContigousArr(arr, badNum);
            Arrays.sort(res);
            System.out.println("encryption weakness is " +(res[0]+res[res.length-1]));
        }
    }

    private static long[] getContigousArr(long[] arr, long badNum) {
        long sum = 0;

        for(int i=0; i< arr.length; i++) {
            sum+=arr[i];
            for(int j=i+1; j<arr.length; j++) {
                sum+=arr[j];
                if(sum == badNum) {
                    return Arrays.copyOfRange(arr, i, j);
                } else if(sum > badNum) break;
            }
            sum = 0;
        }
        return null;
    }

    static boolean twoSum(Queue<Long> nums, long target) {
        Set<Long> set = new HashSet<>();

        for(long num : nums) {
            if(set.contains(target-num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

}
