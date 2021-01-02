import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day22 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day22.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            //part1(input);
            part2(input);
        }
    }

    static void part1(String[] input) {
        Queue<Integer> player1 = new LinkedList<>();
        Queue<Integer> player2 = new LinkedList<>();

        for (int i = 1; i < input.length; i++) {
            while (!input[i].equals("")){
                player1.add(Integer.valueOf(input[i]));
                i++;
            }
            i+=2;
            while (i<input.length) {
                player2.add(Integer.valueOf(input[i]));
                i++;
            }
        }

        while(!player1.isEmpty() && !player2.isEmpty()) {
            if(player1.peek() > player2.peek()) {
                player1.add(player1.poll());
                player1.add(player2.poll());
            } else {
                player2.add(player2.poll());
                player2.add(player1.poll());
            }
        }
       int score;
        if(player1.isEmpty()) {
            score = getScore(player2);
        } else {
            score = getScore(player1);
        }
        System.out.println("Winning player score is " + score);
    }

    private static int getScore(Queue<Integer> player) {
        int sum =0;
        for (int j = player.size(); j > 0; j--) {
            sum += player.poll() * j;
        }
        return sum;
    }

    static void part2(String[] input) {
        Queue<Integer> player1 = new LinkedList<>();
        Queue<Integer> player2 = new LinkedList<>();

        for (int i = 1; i < input.length; i++) {
            while (!input[i].equals("")){
                player1.add(Integer.valueOf(input[i]));
                i++;
            }
            i+=2;
            while (i<input.length) {
                player2.add(Integer.valueOf(input[i]));
                i++;
            }
        }

        startGame(player1, player2);
        int score;
        if(player1.isEmpty()) {
            score = getScore(player2);
        } else {
            score = getScore(player1);
        }
        System.out.println("Winning player score is " + score);
    }

    private static void startGame(Queue<Integer> player1, Queue<Integer> player2) {
        List<String> player1States = new ArrayList<>();
        List<String> player2States = new ArrayList<>();

        while (!player1.isEmpty() && !player2.isEmpty()) {
            if(player1States.contains(player1.toString()) && player2States.contains(player2.toString())) {
                player2.clear();
                break;
            }
            player1States.add(player1.toString());
            player2States.add(player2.toString());
            if(player1.peek() < player1.size() && player2.peek() < player2.size()) {
                Queue<Integer> newPlayer1 = new LinkedList<>();
                Queue<Integer> newPlayer2 = new LinkedList<>();
                Iterator<Integer> iterator1 = player1.iterator();
                Iterator<Integer> iterator2 = player2.iterator();
                iterator1.next();
                iterator2.next();
                int i=1, j=1;
                while(i++<=player1.peek()) newPlayer1.add(iterator1.next());
                while (j++<=player2.peek()) newPlayer2.add(iterator2.next());
                startGame(newPlayer1, newPlayer2);
                if(newPlayer1.isEmpty()) {
                    player2.add(player2.poll());
                    player2.add(player1.poll());
                } else {
                    player1.add(player1.poll());
                    player1.add(player2.poll());
                }
            } else {
                if(player1.peek() > player2.peek()) {
                    player1.add(player1.poll());
                    player1.add(player2.poll());
                } else {
                    player2.add(player2.poll());
                    player2.add(player1.poll());
                }
            }
        }
    }


}
