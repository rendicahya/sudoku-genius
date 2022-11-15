package rc.sudokugenius.sudoku.technique;

import rc.collections.ArrayList;
import rc.sudokugenius.sudoku.operations.PuzzleAnalyzer;

public class NakedSingle {

    public static ArrayList filter(PuzzleAnalyzer puzzleAnalyzer) {
        int[][] puzzle = puzzleAnalyzer.getPuzzle();
        boolean done;
        ArrayList<int[]> list = new ArrayList<int[]>();

        do {
            done = true;
            int[] naked;

            while ((naked = getOne(puzzleAnalyzer)) != null) {
                int row = naked[0];
                int col = naked[1];
                int value = naked[2];

                puzzle[row][col] = value;
                list.add(new int[]{row, col, value});

                puzzleAnalyzer.analyze();
                done = false;

                break;
            }
        } while (!done);

        return list;
    }

    public static int[] getOne(PuzzleAnalyzer puzzleAnalyzer) {
        int[][] puzzle = puzzleAnalyzer.getPuzzle();
        ArrayList<Integer>[] candidates = puzzleAnalyzer.getCandidates();

        outer:
        for (int row = 0, chr = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0) {
                    if (candidates[chr].size() == 1) {
                        return new int[]{row, col, candidates[chr].get(0), 0};
                    }

                    chr++;
                }
            }
        }

        return null;
    }
}