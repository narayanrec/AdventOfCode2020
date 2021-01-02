import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day11Part2 {

    public static void main(String[] args) throws IOException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day11.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));

            char[][] matrix = new char[input.length][input[0].length()];

            for(int i=0; i<input.length; i++) {
                matrix[i] = input[i].toCharArray();
            }
            char[][] newMatrix = new char[matrix.length][matrix[0].length];
            while(true) {
                System.out.println("inside loop");
                for(int i=0; i<matrix.length; i++){
                    for(int j=0; j<matrix[0].length; j++) {
                        newMatrix[i][j] = matrix[i][j];
                        if(matrix[i][j] == 'L') {
                            if(countSeatOccupied(matrix, i, j) == 0){
                                newMatrix[i][j] = '#';
                            }
                        } else if(matrix[i][j] == '#') {
                            if(countSeatOccupied(matrix, i, j) >= 5) {
                                newMatrix[i][j] = 'L';
                            }
                        }

                    }
                }
                if(same(matrix, newMatrix)) break;
                else {
                    matrix = newMatrix;
                    newMatrix = new char[matrix.length][matrix[0].length];
                    continue;
                }
            }
            int count =0;
            for(int i=0; i<matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if(newMatrix[i][j] == '#') count++;
                }
            }
            System.out.println("count is "+count);

        }
    }

    static boolean same(char[][] matrix1, char[][] matrix2) {
        for(int i=0; i<matrix1.length; i++) {
            System.out.println("matrix1 row is  "+Arrays.toString(matrix1[i]));
            System.out.println("matrix2 row is  "+Arrays.toString(matrix2[i]));
            if(!Arrays.toString(matrix1[i]).equals(Arrays.toString(matrix2[i]))) return false;
        }
        return true;
    }

    private static int countSeatOccupied(char[][] matrix, int i, int j) {

        return seatOccupiedLeft(matrix, i, j-1)
                + seatOccupiedRight(matrix, i, j+1)
                +seatOccupiedUp(matrix, i-1, j)
                +seatOccupiedDown(matrix, i+1, j)
                +seatOccupiedUpRight(matrix, i-1, j+1)
                +seatOccupiedDownRight(matrix, i+1, j+1)
                +seatOccupiedUpLeft(matrix, i-1, j-1)
                +seatOccupiedDownLeft(matrix, i+1, j-1)
                ;

    }

    private static int seatOccupiedLeft(char[][] matrix, int i, int j) {
        if(i<0 || i>=matrix.length || j<0 ||j>=matrix[0].length || matrix[i][j] == 'L') return 0;
        if(matrix[i][j] == '#') return 1;

        return  seatOccupiedLeft(matrix, i, j-1);
    }

    private static int seatOccupiedRight(char[][] matrix, int i, int j) {
        if(i<0 || i>=matrix.length || j<0 ||j>=matrix[0].length || matrix[i][j] == 'L') return 0;
        if(matrix[i][j] == '#') return 1;

        return  seatOccupiedRight(matrix, i, j+1);
    }

    private static int seatOccupiedUp(char[][] matrix, int i, int j) {
        if(i<0 || i>=matrix.length || j<0 ||j>=matrix[0].length || matrix[i][j] == 'L') return 0;
        if(matrix[i][j] == '#') return 1;

        return  seatOccupiedUp(matrix, i-1, j);
    }

    private static int seatOccupiedDown(char[][] matrix, int i, int j) {
        if(i<0 || i>=matrix.length || j<0 ||j>=matrix[0].length || matrix[i][j] == 'L') return 0;
        if(matrix[i][j] == '#') return 1;

        return  seatOccupiedDown(matrix, i+1, j);
    }

    private static int seatOccupiedUpRight(char[][] matrix, int i, int j) {
        if(i<0 || i>=matrix.length || j<0 ||j>=matrix[0].length || matrix[i][j] == 'L') return 0;
        if(matrix[i][j] == '#') return 1;

        return  seatOccupiedUpRight(matrix, i-1, j+1);
    }

    private static int seatOccupiedDownRight(char[][] matrix, int i, int j) {
        if(i<0 || i>=matrix.length || j<0 ||j>=matrix[0].length || matrix[i][j] == 'L') return 0;
        if(matrix[i][j] == '#') return 1;

        return  seatOccupiedDownRight(matrix, i+1, j+1);
    }

    private static int seatOccupiedUpLeft(char[][] matrix, int i, int j) {
        if(i<0 || i>=matrix.length || j<0 ||j>=matrix[0].length || matrix[i][j] == 'L') return 0;
        if(matrix[i][j] == '#') return 1;

        return  seatOccupiedUpLeft(matrix, i-1, j-1);
    }

    private static int seatOccupiedDownLeft(char[][] matrix, int i, int j) {
        if(i<0 || i>=matrix.length || j<0 ||j>=matrix[0].length || matrix[i][j] == 'L') return 0;
        if(matrix[i][j] == '#') return 1;

        return  seatOccupiedDownLeft(matrix, i+1, j-1);
    }

}
