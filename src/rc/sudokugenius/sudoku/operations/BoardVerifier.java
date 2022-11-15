package rc.sudokugenius.sudoku.operations;

import rc.collections.ArrayList;

public class BoardVerifier {

    /**
     * Memverifikasi board yang telah lengkap.
     * 
     * @param values array 9x9, isi dari puzzle
     * @return list yang berisi sel-sel invalid
     */
    public static ArrayList<int[]> verify(int[][] values) {
        ArrayList<int[]> invalid = new ArrayList<int[]>();

        for (int row = 0; row < 9; row++) {
            cell:
            for (int col = 0; col < 9; col++) {
                final int cellValue = values[row][col];

                for (int i = 0; i < 9; i++) {
                    if (i != col && values[row][i] == cellValue) {
                        invalid.add(new int[]{row, col});

                        continue cell;
                    }

                    if (i != row && values[i][col] == cellValue) {
                        invalid.add(new int[]{row, col});

                        continue cell;
                    }
                }

                int colStart = col < 3 ? 0 : col < 6 ? 3 : 6;
                int colEnd = col < 3 ? 3 : col < 6 ? 6 : 9;
                int rowStart = row < 3 ? 0 : row < 6 ? 3 : 6;
                int rowEnd = row < 3 ? 3 : row < 6 ? 6 : 9;

                for (int r = rowStart; r < rowEnd; r++) {
                    for (int c = colStart; c < colEnd; c++) {
                        if (r != row && c != col && cellValue == values[r][c]) {
                            invalid.add(new int[]{row, col});

                            continue cell;
                        }
                    }
                }
            }
        }

        return invalid;
    }
}