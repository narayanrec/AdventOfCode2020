import java.io.IOException;
import java.util.*;

public class Day15 {

    public static void main(String[] args) throws IOException, InterruptedException {
        int[] nums = {16,12,1,0,15,7,11};
        printNthNumber(nums, 2020);
        printNthNumber(nums, 30000000);
    }

    static void printNthNumber(int[] nums, int n) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length-1; i++)
            map.put(nums[i], i+1);
        int i=nums.length;
        int lastSpoken = nums[nums.length-1];
        while(i<n) {
            if(map.containsKey(lastSpoken)){
                int lastSpokenIndex = map.get(lastSpoken);
                map.put(lastSpoken, i);
                lastSpoken = i-lastSpokenIndex;

            } else {
                map.put(lastSpoken, i);
                lastSpoken = 0;

            }
            i++;
        }

        System.out.println(n +"th number spoken is " +lastSpoken);

    }

}
