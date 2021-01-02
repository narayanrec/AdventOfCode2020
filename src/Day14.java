import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day14 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day14.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            //part1(input);
            part2(input);
        }
    }

    static void part1(String[] input) {
        char[] mask = new char[36];
        Map<Long, Long> res = new HashMap<>();
        for(String str: input) {
            if(str.startsWith("mask")) {
                mask = str.split("\\s")[2].toCharArray();
            } else if(str.startsWith("mem")) {
                String[] parts = str.split("\\s");
                long loc = Long.valueOf(parts[0].substring(4, parts[0].length()-1));
                String binStr = String.format("%36s", Long.toBinaryString(Long.valueOf(parts[2]))).replaceAll(" ", "0");

                StringBuilder sb = new StringBuilder();
                for(int i=0; i<36; i++) {
                    if(mask[i] != 'X') sb.append(mask[i]);
                    else sb.append(binStr.charAt(i));
                }
                res.put(loc, Long.valueOf(sb.toString(), 2));
                System.out.println("result is "+res.values().stream().mapToLong(it -> it).sum());

            }
        }
    }

    static void part2(String[] input) {
        char[] mask = new char[36];
        Map<Long, Long> res = new HashMap<>();
        for(String str: input) {
            if(str.startsWith("mask")) {
                mask = str.split("\\s")[2].toCharArray();
            } else if(str.startsWith("mem")) {
                String[] parts = str.split("\\s");
                String binStrLoc = String.format("%36s", Long.toBinaryString(Long.valueOf(parts[0].substring(4, parts[0].length()-1)))).replaceAll(" ", "0");
                long val = Long.valueOf(parts[2]);
                System.out.println("loc is" + binStrLoc);
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<36; i++) {
                    if(mask[i] != '0') sb.append(mask[i]);
                    else sb.append(binStrLoc.charAt(i));
                }

                System.out.println("loc after is " + sb.toString());
                List<String> combs = decodeAddresses(sb.toString());

                for(String key: combs) {
                    res.put(Long.valueOf(key, 2), val);
                }

            }
        }
        System.out.println("result is "+res.values().stream().mapToLong(it -> it).sum());
    }

    private static List<String> decodeAddresses(String maskedAdd) {
        List<String> ret = new ArrayList<>();
        ret.add("");
        for(int i=0; i<maskedAdd.length(); i++) {

            if(maskedAdd.charAt(i) == 'X') {
                int len = ret.size();
                for(int j=0; j<len; j++) {
                    String str = ret.remove(0);
                    ret.add(str + "0");
                    ret.add(str + "1");
                }
            } else if(maskedAdd.charAt(i) == '1') {
                for(int j=0; j<ret.size();j++)
                    ret.set(j, ret.get(j)+"1");
            } else {
                for(int j=0; j<ret.size();j++)
                    ret.set(j, ret.get(j)+maskedAdd.charAt(i));
            }
        }
        return ret;
    }


}
