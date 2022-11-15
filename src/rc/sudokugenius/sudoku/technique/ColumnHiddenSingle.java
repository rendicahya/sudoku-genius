package rc.sudokugenius.sudoku.technique;

import java.util.HashMap;
import rc.collections.ArrayList;
import rc.sudokugenius.sudoku.operations.PuzzleAnalyzer;

public class ColumnHiddenSingle {

    public static ArrayList<int[]> filter(PuzzleAnalyzer puzzleAnalyzer) {
        int[][] puzzle = puzzleAnalyzer.getPuzzle();
        boolean done;
        int count = 0;
        ArrayList<int[]> list = new ArrayList<int[]>();

        do {
            done = true;
            int[] hidden;

            while ((hidden = getOne(puzzleAnalyzer)) != null) {
                int row = hidden[0];
                int col = hidden[1];
                int value = hidden[2];

                puzzle[row][col] = value;
                list.add(new int[]{row, col, value});

                puzzleAnalyzer.analyze();
                done = false;
                count++;

                break;
            }
        } while (!done);

        return list;
    }

    public static int[] getOne(PuzzleAnalyzer puzzleAnalyzer) {
        int[][] puzzle = puzzleAnalyzer.getPuzzle();
        ArrayList<Integer>[] candidates = puzzleAnalyzer.getCandidates();
        ArrayList<Integer> alreadyAdded = new ArrayList<Integer>();
        ArrayList<Integer> unique = new ArrayList<Integer>();
        HashMap<Integer, Integer> own = new HashMap<Integer, Integer>();
        HashMap<Integer, ArrayList<Integer>> candidatesMatrix = new HashMap<Integer, ArrayList<Integer>>();

        for (int row = 0, chr = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0) {
                    candidatesMatrix.put(row * 10 + col, candidates[chr++]);
                }
            }
        }

        for (int col = 0, chr = 0; col < 9; col++) {
            alreadyAdded.clear();
            unique.clear();
            own.clear();

            for (int row = 0; row < 9; row++) {
                if (puzzle[row][col] == 0) {
                    for (int val : candidatesMatrix.get(row * 10 + col)) {
                        if (!alreadyAdded.contains(val)) {
                            unique.add(val);
                            own.put(val, row);
                            alreadyAdded.add(val);
                        } else if (unique.contains(val)) {
                            unique.remove(new Integer(val));
                            own.remove(val);
                        }
                    }

                    chr++;
                }
            }

            if (!unique.isEmpty()) {
                for (int value : unique) {
                    int row = own.get(value);
                    puzzle[row][col] = value;

                    return new int[]{row, col, value, 2};
                }
            }
        }

        return null;
    }
}