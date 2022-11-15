package rc.sudokugenius.sudoku.operations;

import rc.collections.ArrayList;

public class CellCandidatesFinder {

    public static ArrayList<Integer> find(int[][] puzzle, int row, int col) {
        if (puzzle[row][col] == 0) {
            ArrayList<Integer> candidates = new ArrayList<Integer>();
            candidates.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);

            for (int i = 0; i < 9; i++) {
                if (i != col && puzzle[row][i] != 0) {
                    candidates.remove(new Integer(puzzle[row][i]));
                }

                if (i != row && puzzle[i][col] != 0) {
                    candidates.remove(new Integer(puzzle[i][col]));
                }
            }

            int colStart = col < 3 ? 0 : col < 6 ? 3 : 6;
            int colEnd = col < 3 ? 3 : col < 6 ? 6 : 9;
            int rowStart = row < 3 ? 0 : row < 6 ? 3 : 6;
            int rowEnd = row < 3 ? 3 : row < 6 ? 6 : 9;

            for (int r = rowStart; r < rowEnd; r++) {
                for (int c = colStart; c < colEnd; c++) {
                    if (r != row && c != col && puzzle[r][c] != 0) {
                        candidates.remove(new Integer(puzzle[r][c]));
                    }
                }
            }

            return candidates;
        } else {
            return null;
        }
    }
}