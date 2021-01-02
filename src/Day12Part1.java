import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Day12Part1 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day12.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            Instruction[] instructions = new Instruction[input.length];

            for(int i=0; i<input.length; i++) {
                instructions[i] = new Instruction(input[i].charAt(0), Integer.valueOf(input[i].substring(1)));
            }

            part1(instructions);
            part2(instructions);

        }
    }

    private static void part1(Instruction[] instructions) {
        Boat boat = new Boat();

        for(Instruction ins: instructions) {
            boat.execute(ins);
        }

        int res = Math.abs(boat.x) + Math.abs(boat.y);

        System.out.println("manhattan dist is "+ res);
    }

    private static void part2(Instruction[] instructions) {
        Boat boat = new Boat();

        for(Instruction ins: instructions) {
            boat.executeWaypoint(ins);
        }

        int res = Math.abs(boat.x) + Math.abs(boat.y);

        System.out.println("manhattan dist is "+ res);
    }

    private static class Instruction {
        final char moveType;
        final int distance;

        Instruction(char moveType, int distance) {
            this.moveType = moveType;
            this.distance = distance;
        }
    }

    private static class Boat{
        static Map<Integer, Character> dir = new HashMap(){{
            put(0, 'E');
            put(90, 'S');
            put(180, 'W');
            put(270, 'N');
        }};
        int x;
        int y;
        int wx=10;
        int wy=1;
        char forwardDir = 'E';
        int currAngle = 0;
        void execute(Instruction instruction) {
            char moveType = instruction.moveType;
            int distance = instruction.distance;
            if(moveType == 'F') {
                if(forwardDir == 'E') x+=distance;
                else if(forwardDir == 'W') x-=distance;
                else if(forwardDir == 'N') y+=distance;
                else y-=distance;
            } else if(moveType == 'N') {
                y+=distance;
            } else if(moveType == 'S') {
                y-=distance;
            }else if(moveType == 'W') {
                x-=distance;
            } else if(moveType == 'E') {
                x+=distance;
            } else if(moveType == 'R') {
                currAngle += distance;
                currAngle%=360;
                forwardDir = dir.get(currAngle);
            } else if(moveType == 'L') {
                currAngle -=distance;
                if(currAngle < 0) currAngle = 360+currAngle;
                forwardDir = dir.get(currAngle);
            }
        }

        void executeWaypoint(Instruction instruction) {
            char code = instruction.moveType;
            int val = instruction.distance;
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
    }

}
