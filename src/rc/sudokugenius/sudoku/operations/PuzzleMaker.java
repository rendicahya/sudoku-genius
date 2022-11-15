package rc.sudokugenius.sudoku.operations;

import rc.collections.ArrayList;
import rc.sudokugenius.sudoku.PuzzleType;

public class PuzzleMaker {

    public static int[][] makePuzzle(int[][] solution, int difficulty, int type) {
        int[][] puzzle = new int[9][9];

        ArrayList<Integer> emptyCell = null;

        switch (type) {
            case PuzzleType.RANDOM:
                emptyCell = random(difficulty);
                break;
            case PuzzleType.TWOTWO:
                emptyCell = twotwo();
                break;
            default:
                emptyCell = heart();
        }

        for (int row = 0, cell = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++, cell++) {
                puzzle[row][col] = emptyCell.contains(cell) ? 0 : solution[row][col];
            }
        }

        return puzzle;
    }

    private static ArrayList<Integer> random(int emptyCellsNum) {
        ArrayList<Integer> emptyCell = new ArrayList<Integer>();
        ArrayList<Integer> available = new ArrayList<Integer>();

        for (int i = 0; i < 81; i++) {
            available.add(i);
        }

        for (int i = 0; i < emptyCellsNum; i++) {
            emptyCell.add(available.getRandomAndRemove());
        }

        return emptyCell;
    }

    private static ArrayList<Integer> twotwo() {
        ArrayList<Integer> emptyCell = new ArrayList<Integer>();

        emptyCell.addAll(
                0, 3, 4, 5, 8,
                10, 11, 13, 15, 16,
                18, 19, 20, 22, 23, 24, 25,
                27, 28, 29, 31, 32, 33, 34,
                36, 37, 38, 40, 41, 42, 43,
                45, 46, 48, 49, 50, 51, 53,
                54, 56, 57, 58, 59, 61, 62,
                64, 65, 66, 67, 69, 70, 71,
                76);

        return emptyCell;
    }

    private static ArrayList<Integer> heart() {
        ArrayList<Integer> emptyCell = new ArrayList<Integer>();

        emptyCell.addAll(
                0, 4, 8,
                10, 11, 12, 14, 15, 16,
                19, 20, 21, 22, 23, 24, 25,
                28, 29, 30, 31, 32, 33, 34,
                37, 38, 39, 40, 41, 42, 43,
                45, 47, 48, 49, 50, 51, 53,
                54, 55, 57, 58, 59, 61, 62,
                63, 64, 65, 67, 69, 70, 71,
                72, 73, 74, 75, 77, 78, 79, 80);

        return emptyCell;
    }
}