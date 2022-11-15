package rc.sudokugenius.sudoku.operations;

import rc.collections.ArrayList;

public class PuzzleGenerator {

    public static int[][] generate() {
        ArrayList<Integer>[][] slots = new ArrayList[9][9];
        int[][] values = new int[9][9];
        boolean error;

        outer:
        do {
            error = false;

            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    slots[row][col] = new ArrayList<Integer>();
                    slots[row][col].addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
                }
            }

            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (slots[row][col].isEmpty()) {
                        error = true;

                        continue outer;
                    }

                    int rand = slots[row][col].getRandomAndRemove();

                    for (int c = col + 1; c < 9; c++) {
                        if (slots[row][c].contains(new Integer(rand))) {
                            slots[row][c].remove(new Integer(rand));
                        }
                    }

                    for (int r = row + 1; r < 9; r++) {
                        if (slots[r][col].contains(new Integer(rand))) {
                            slots[r][col].remove(new Integer(rand));
                        }
                    }

                    int colStart = col < 3 ? 0 : col < 6 ? 3 : 6;
                    int colEnd = col < 3 ? 3 : col < 6 ? 6 : 9;
                    int rowStart = row < 3 ? 0 : row < 6 ? 3 : 6;
                    int rowEnd = row < 3 ? 3 : row < 6 ? 6 : 9;

                    for (int r = rowStart; r < rowEnd; r++) {
                        for (int c = colStart; c < colEnd; c++) {
                            if (slots[r][c].contains(new Integer(rand))) {
                                slots[r][c].remove(new Integer(rand));
                            }
                        }
                    }

                    values[row][col] = rand;
                }
            }
        } while (error);

        return values;
    }
}