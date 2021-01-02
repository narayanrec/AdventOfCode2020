import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day11Part1 {

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
                            if(isEmpty(matrix, i, j)){
                                newMatrix[i][j] = '#';
                            }
                        } else if(matrix[i][j] == '#') {
                            if(countSeatOccupied(matrix, i, j) >= 4) {
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
            //System.out.println("matrix1 row is  "+Arrays.toString(matrix1[i]));
            //System.out.println("matrix2 row is  "+Arrays.toString(matrix2[i]));
            if(!Arrays.toString(matrix1[i]).equals(Arrays.toString(matrix2[i]))) return false;
        }
        return true;
    }

    static boolean isEmpty(char[][] matrix, int i, int j) {
        int m=matrix.length;
        int n=matrix[0].length;
        if(i>0 && matrix[i-1][j] != 'L' && matrix[i-1][j] != '.') return false;

        if(i<m-1 && matrix[i+1][j] != 'L' && matrix[i+1][j] != '.') return false;

        if(j>0 && matrix[i][j-1] != 'L' && matrix[i][j-1] != '.') return false;

        if(j<n-1 && matrix[i][j+1] != 'L' && matrix[i][j+1] != '.') return false;

        if(i>0 && j>0 && matrix[i-1][j-1] != 'L' && matrix[i-1][j-1] != '.') return false;

        if(i<m-1 && j<n-1 && matrix[i+1][j+1] != 'L' && matrix[i+1][j+1] != '.') return false;

        if(i>0 && j<n-1 && matrix[i-1][j+1] != 'L' && matrix[i-1][j+1] != '.') return false;

        if(i<m-1 && j>0 && matrix[i+1][j-1] != 'L' && matrix[i+1][j-1] != '.') return false;

        return true;
    }

    private static int countSeatOccupied(char[][] matrix, int i, int j) {
        int m=matrix.length;
        int n=matrix[0].length;
        int count =0;
        if(i>0 && matrix[i-1][j] == '#') count++;

        if(i<m-1 && matrix[i+1][j] == '#') count++;

        if(j>0 && matrix[i][j-1] == '#' ) count++;

        if(j<n-1 && matrix[i][j+1] == '#') count++;

        if(i>0 && j>0 && matrix[i-1][j-1] == '#') count++;

        if(i<m-1 && j<n-1 && matrix[i+1][j+1] == '#') count++;

        if(i>0 && j<n-1 && matrix[i-1][j+1] == '#') count++;

        if(i<m-1 && j>0 && matrix[i+1][j-1] == '#') count++;

        return count;
    }

}
