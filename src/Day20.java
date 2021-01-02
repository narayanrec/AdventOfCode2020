import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day20 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "//Users//narayanprangya//IdeaProjects//AdventOfCode//src//input_day20.txt";

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            String[] input = stream.toArray(String[]::new);
            System.out.println("input is " + Arrays.toString(input));
            //part1(input);
            part2(input);
        }
    }

    static void part1(String[] input) {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            int id = Integer.valueOf(input[i].substring(5, 9));
            int j=0;
            i++;
            char[][] tile = new char[10][10];
            while(i< input.length && !input[i].equals("")) {
                tile[j++] = input[i].toCharArray();
                i++;
            }
            tiles.add(new Tile(id, tile));
        }
        int[] ids = cornerTiles(tiles);
        long product =1;
        for (int i = 0; i < ids.length; i++) {
            product*=ids[i];
        }
        System.out.println("product is " +product);
    }

    static int[] cornerTiles(List<Tile> tiles) {
        int[] ids = new int[4];
        int i=0;
        for (Tile tile : tiles) {
            if(matchBorders(tile, tiles) == 2) {
                tile.corner = true;
                ids[i++] = tile.id;
            }
        }
        return ids;
    }

    private static int matchBorders(Tile tile, List<Tile> tiles) {
        int count =0;

        for(Tile otherTile: tiles) {
            if(tile.id != otherTile.id && tile.matchBorders(otherTile)) count++;
        }
        return count;
    }

    static void part2(String[] input) {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            int id = Integer.valueOf(input[i].substring(5, 9));
            int j=0;
            i++;
            char[][] grid = new char[10][10];
            while(i< input.length && !input[i].equals("")) {
                grid[j++] = input[i].toCharArray();
                i++;
            }
            Tile tile = new Tile(id, grid);
            tiles.add(tile);
        }
        cornerTiles(tiles);

        Tile cornerTile = tiles.stream().filter(it -> it.corner).findFirst().get();
        int size = (int)Math.sqrt(tiles.size());
        Tile[][] puzzle = new Tile[size][size];
        puzzle[0][0] = cornerTile;

        tiles.remove(cornerTile);
        int topMatches = 0;
        int rightMatches = 0;
        int bottomMatches = 0;
        int leftMatches = 0;
        for(Tile other: tiles) {
            Tile tlc = puzzle[0][0];
            if(tlc.matchTopBorder(other))
                topMatches++;
            else if(tlc.matchRightBorder(other))
                rightMatches++;
            else if(tlc.matchBottomBorder(other))
                bottomMatches++;
            else if(tlc.matchLeftBorder(other))
                leftMatches++;

        }
        if(topMatches == 0 && rightMatches == 0)  puzzle[0][0].rotateClockwise(3);
        else if(rightMatches == 0 && bottomMatches == 0) puzzle[0][0].rotateClockwise(2);
        else if(bottomMatches == 0 && leftMatches == 0) puzzle[0][0].rotateClockwise(1);

        for (int r = 0; r < puzzle.length; r++) {
            for (int c = 0; c < puzzle[0].length; c++) {
                if(puzzle[r][c] == null) {
                    if(r ==0) {
                        String edgeToMatch = puzzle[r][c-1].right;
                        int i=0;
                        boolean pieceFound = false;
                        while (!pieceFound) {
                            Tile tile = tiles.get(i);
                            if(tile.top.equals(edgeToMatch)) {
                                tile.flipHorizontal();
                                tile.rotateClockwise(3);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            }else if(tile.right.equals(edgeToMatch)) {
                                tile.flipHorizontal();
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(tile.bottom.equals(edgeToMatch)) {
                                tile.rotateClockwise(1);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(tile.left.equals(edgeToMatch)) {
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(rev(tile.top).equals(edgeToMatch)) {
                                tile.rotateClockwise(3);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(rev(tile.right).equals(edgeToMatch)) {
                                tile.rotateClockwise(2);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(rev(tile.bottom).equals(edgeToMatch)) {
                                tile.flipHorizontal();
                                tile.rotateClockwise(1);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(rev(tile.left).equals(edgeToMatch)) {
                                tile.flipHorizontal();
                                tile.rotateClockwise(2);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            }
                            i++;
                        }
                    } else {
                        String edgeToMatch = puzzle[r-1][c].bottom;
                        int i=0;
                        boolean pieceFound = false;
                        while (!pieceFound) {
                            Tile tile = tiles.get(i);
                            if(tile.top.equals(edgeToMatch)) {
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            }else if(tile.right.equals(edgeToMatch)) {
                                tile.rotateClockwise(3);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(tile.bottom.equals(edgeToMatch)) {
                                tile.flipHorizontal();
                                tile.rotateClockwise(2);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(tile.left.equals(edgeToMatch)) {
                                tile.rotateClockwise(1);
                                tile.flipHorizontal();
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(rev(tile.top).equals(edgeToMatch)) {
                                tile.flipHorizontal();
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(rev(tile.right).equals(edgeToMatch)) {
                                tile.flipHorizontal();
                                tile.rotateClockwise(1);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(rev(tile.bottom).equals(edgeToMatch)) {
                                tile.rotateClockwise(2);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            } else if(rev(tile.left).equals(edgeToMatch)) {
                                tile.rotateClockwise(1);
                                puzzle[r][c] = tile;
                                pieceFound = true;
                                tiles.remove(i);
                            }
                            i++;
                        }

                    }
                }
            }
        }

        char[][] image = new char[puzzle.length*8][puzzle.length*8];
        int ri=0;
        int ci =0;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle.length; j++) {
                char[][] g = puzzle[i][j].image;

                ri = i*8;
                ci = j*8;
                for (int r = 1; r <= g.length-2; r++) {
                    for (int c = 1; c <= g[0].length - 2; c++) {
                        image[ri][ci] = g[r][c];
                        ci++;
                    }
                    ri++;
                    ci = j*8;
                }
            }
        }

        Tile completedPuzzle = new Tile(0, image );


        completedPuzzle.findMonsters();
        completedPuzzle.rotateClockwise(1);
        completedPuzzle.findMonsters();
        completedPuzzle.rotateClockwise(1);
        completedPuzzle.findMonsters();
        completedPuzzle.rotateClockwise(1);
        completedPuzzle.findMonsters();
        completedPuzzle.rotateClockwise(1);

        completedPuzzle.flipHorizontal();
        completedPuzzle.findMonsters();
        completedPuzzle.rotateClockwise(1);
        completedPuzzle.findMonsters();
        completedPuzzle.rotateClockwise(1);
        completedPuzzle.findMonsters();
        completedPuzzle.rotateClockwise(1);
        completedPuzzle.findMonsters();

        int count =0;
        char[][] g = completedPuzzle.image;
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                if(g[i][j] == '#') count++;
            }
        }
        System.out.println("water roughness is " +count);
    }

    static String rev(String edge) {
        return new StringBuilder(edge).reverse().toString();
    }

    static class Tile {
        int id;
        char[][] image ;
        String top;
        String right;
        String bottom;
        String left;
        boolean corner;

        public Tile(int id, char[][] image) {
            this.id = id;
            this.image = image;
            top=right=bottom=left="";
            for (int i = 0; i < image.length; i++) {
                left+=image[i][0];
                right+=image[i][image[0].length-1];
            }
            for (int i = 0; i < image[0].length; i++) {
                top+=image[0][i];
                bottom+=image[image.length-1][i];
            }

        }

        boolean matchBorders(Tile other) {
            return matchTopBorder(other) || matchRightBorder(other) || matchBottomBorder(other) || matchLeftBorder(other);
        }

        boolean matchTopBorder(Tile other) {
            String revTop = rev(top);
            if(top.equals(other.top) || revTop.equals(other.top)) return true;
            if(top.equals(other.right) || revTop.equals(other.right)) return true;
            if(top.equals(other.bottom) || revTop.equals(other.bottom)) return true;
            if(top.equals(other.left) || revTop.equals(other.left)) return true;

            return false;
        }

        boolean matchRightBorder(Tile other) {
            String revRight = rev(right);
            if(right.equals(other.top) || revRight.equals(other.top)) return true;
            if(right.equals(other.right) || revRight.equals(other.right)) return true;
            if(right.equals(other.bottom) || revRight.equals(other.bottom)) return true;
            if(right.equals(other.left) || revRight.equals(other.left)) return true;

            return false;
        }

        boolean matchBottomBorder(Tile other) {
            String revBottom = rev(bottom);
            if(bottom.equals(other.top) || revBottom.equals(other.top)) return true;
            if(bottom.equals(other.right) || revBottom.equals(other.right)) return true;
            if(bottom.equals(other.bottom) || revBottom.equals(other.bottom)) return true;
            if(bottom.equals(other.left) || revBottom.equals(other.left)) return true;

            return false;
        }

        boolean matchLeftBorder(Tile other) {
            String revLeft = rev(left);
            if(left.equals(other.top) || revLeft.equals(other.top)) return true;
            if(left.equals(other.right) || revLeft.equals(other.right)) return true;
            if(left.equals(other.bottom) || revLeft.equals(other.bottom)) return true;
            if(left.equals(other.left) || revLeft.equals(other.left)) return true;

            return false;
        }

        String rev(String edge) {
            return new StringBuilder(edge).reverse().toString();
        }

        void rotateClockwise(int i) {
            for (int j = 0; j < i; j++) {
                char[][] copy = new char[image.length][image[0].length];
                for (int row = 0; row < copy.length; row++) {
                    for (int col = 0; col < copy[0].length; col++) {
                        copy[row][col] = image[image.length-1-col][row];
                    }
                }

                String temp = top;
                top= rev(left);
                left = bottom;
                bottom = rev(right);
                right = temp;

                image = copy;
            }
        }

        void flipHorizontal() {
            char[][] copy = new char[image.length][image[0].length];
            for (int row = 0; row < copy.length; row++) {
                for (int col = 0; col < copy[0].length; col++) {
                    copy[row][col] = image[row][image.length-1-col];
                }
            }
            String temp = left;
            left = right;
            right = temp;
            top = rev(top);
            bottom = rev(bottom);
            image = copy;
        }

        public void findMonsters() {
            for (int r = 0; r < image.length; r++) {
                for (int c = 0; c < image[0].length; c++) {
                    if (c + 19 < image[0].length && r + 2 < image.length)
                        if (image[r][c + 18] == '#' && image[r + 1][c] == '#' && image[r + 1][c + 5] == '#'
                                && image[r + 1][c + 6] == '#' && image[r + 1][c + 11] == '#' && image[r + 1][c + 12] == '#'
                                && image[r + 1][c + 17] == '#' && image[r + 1][c + 18] == '#' && image[r + 1][c + 19] == '#'
                                && image[r + 2][c + 1] == '#' && image[r + 2][c + 4] == '#' && image[r + 2][c + 7] == '#'
                                && image[r + 2][c + 10] == '#' && image[r + 2][c + 13] == '#' && image[r + 2][c + 16] == '#') {

                            image[r][c + 18] = 'O';
                            image[r + 1][c] = 'O';
                            image[r + 1][c + 5] = 'O';
                            image[r + 1][c + 6] = 'O';
                            image[r + 1][c + 11] = 'O';
                            image[r + 1][c + 12] = 'O';
                            image[r + 1][c + 17] = 'O';
                            image[r + 1][c + 18] = 'O';
                            image[r + 1][c + 19] = 'O';
                            image[r + 2][c + 1] = 'O';
                            image[r + 2][c + 4] = 'O';
                            image[r + 2][c + 7] = 'O';
                            image[r + 2][c + 10] = 'O';
                            image[r + 2][c + 13] = 'O';
                            image[r + 2][c + 16] = 'O';

                        }
                }
            }

        }
    }



}
