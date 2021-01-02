import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day16 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day16.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            //part1(input);
            part2(input);
        }
    }

    static void part1(String[] input) {
        Map<String, List<Pair>> validValues = new HashMap<>();
        String yourTicket;
        List<Integer> nearbyTickets = new ArrayList<>();

        int ticketScanningErrorRate = 0;
        for(int i=0; i<input.length; i++) {
            while(!input[i].equals("")) {
                String[] parts = input[i].split(":");
                validValues.putIfAbsent(parts[0], new ArrayList<>());
                String[] parts1 =parts[1].trim().split("or");
                String[] ranges1 = parts1[0].trim().split("-");
                String[] ranges2 = parts1[1].trim().split("-");
                validValues.get(parts[0]).add(new Pair(Integer.valueOf(ranges1[0]), Integer.valueOf(ranges1[1])));
                validValues.get(parts[0]).add(new Pair(Integer.valueOf(ranges2[0]), Integer.valueOf(ranges2[1])));
                i++;
            }
            i+=2;
            yourTicket = input[i];
            i+=3;
            while(i<input.length) {
                String[] vals = input[i].split(",");
                for(String val : vals) {
                    if(!isValid(Integer.valueOf(val), validValues)) ticketScanningErrorRate += Integer.valueOf(val);
                }
                i++;
            }
        }
        System.out.println("ticket scanning error rate is " +ticketScanningErrorRate);
    }

    static void part2(String[] input) {
        Map<String, List<Pair>> validValues = new HashMap<>();
        List<List<Integer>> validTickets = new ArrayList<>();
        List<Integer> myTicket = new ArrayList<>();
        for(int i=0; i<input.length; i++) {
            while(!input[i].equals("")) {
                String[] parts = input[i].split(":");
                validValues.putIfAbsent(parts[0], new ArrayList<>());
                String[] parts1 =parts[1].trim().split("or");
                String[] ranges1 = parts1[0].trim().split("-");
                String[] ranges2 = parts1[1].trim().split("-");
                validValues.get(parts[0]).add(new Pair(Integer.valueOf(ranges1[0]), Integer.valueOf(ranges1[1])));
                validValues.get(parts[0]).add(new Pair(Integer.valueOf(ranges2[0]), Integer.valueOf(ranges2[1])));
                i++;
            }
            i+=2;
            String[] myTicketVals= input[i].split(",");
            for(String val : myTicketVals) myTicket.add(Integer.valueOf(val));
            i+=3;
            while(i<input.length) {
                String[] vals = input[i].split(",");
                boolean valid = true;
                List<Integer> ticket = new ArrayList<>();
                for(String val : vals) {
                    ticket.add(Integer.valueOf(val));
                    if(!isValid(Integer.valueOf(val), validValues)) valid = false;
                }
                if(valid) validTickets.add(ticket);
                i++;
            }
        }
        long product = 1;
        String[] order = determineFieldOrder(validTickets, validValues);
        for(int i=0; i<order.length; i++) {
            if(order[i].startsWith("departure")) {
                System.out.println("field is " + order[i]);
                System.out.println("val is " + myTicket.get(i));
                product*=myTicket.get(i);
            }
        }
        System.out.println("product is  " +product);
    }

    private static String[] determineFieldOrder(List<List<Integer>> validTickets, Map<String, List<Pair>> validValues) {
        Set<String> fields = validValues.keySet();
        int n = validTickets.get(0).size();
        String[] ret = new String[n];
        Set<String> taken = new HashSet<>();

        Map<Integer, List<String>> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            for(String field: fields) {
                boolean valid = true;
                for(List<Integer> ticket: validTickets) {
                    if(!isValidForField(ticket.get(i), validValues.get(field)) ) {
                        valid = false;
                        break;
                    }
                }
                if(valid) {
                    map.putIfAbsent(i, new ArrayList<>());
                    map.get(i).add(field);
                }
            }
        }
        while(!map.isEmpty()) {
            for(int i=0; i<n; i++) {
                if(map.get(i) != null) {
                    if(map.get(i).size() == 1) {
                        ret[i] = map.get(i).get(0);
                        taken.add(map.get(i).get(0));
                        map.remove(i);
                    } else {
                        map.get(i).removeAll(taken);
                    }
                }

            }
        }
        return ret;
    }

    private static boolean isValidForField(Integer val, List<Pair> pairs) {
        for(Pair pair: pairs) {
            if(val >= (Integer) pair.getKey() && val <= (Integer) pair.getValue()) return true;
        }
        return false;
    }

    private static boolean isValid(Integer val, Map<String, List<Pair>> validValues) {
        for(String key: validValues.keySet()) {
            List<Pair> pairs = validValues.get(key);
            for(Pair pair: pairs) {
                if(val >= (Integer) pair.getKey() && val <= (Integer) pair.getValue()) return true;
            }
        }
        return false;
    }



}
