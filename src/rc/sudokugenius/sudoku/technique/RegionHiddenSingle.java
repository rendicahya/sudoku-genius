package rc.sudokugenius.sudoku.technique;

import java.util.HashMap;
import rc.collections.ArrayList;
import rc.sudokugenius.sudoku.operations.PuzzleAnalyzer;

public class RegionHiddenSingle {

    public static ArrayList<int[]> filter(PuzzleAnalyzer puzzleAnalyzer) {
        int[][] puzzle = puzzleAnalyzer.getPuzzle();
        boolean done;
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
        HashMap<Integer, int[]> own = new HashMap<Integer, int[]>();
        HashMap<Integer, ArrayList<Integer>> candidatesMatrix = new HashMap<Integer, ArrayList<Integer>>();

        for (int row = 0, chr = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0) {
                    candidatesMatrix.put(row * 10 + col, candidates[chr++]);
                }
            }
        }

        for (int rowStart = 0, rowEnd = rowStart + 3; rowStart < 9; rowStart += 3, rowEnd += 3) {
            for (int colStart = 0, colEnd = colStart + 3; colStart < 9; colStart += 3, colEnd += 3) {
                alreadyAdded.clear();
                unique.clear();
                own.clear();

                for (int row = rowStart; row < rowEnd; row++) {
                    for (int col = colStart; col < colEnd; col++) {
                        if (puzzle[row][col] == 0) {
                            for (int val : candidatesMatrix.get(row * 10 + col)) {
                                if (!alreadyAdded.contains(val)) {
                                    unique.add(val);
                                    own.put(val, new int[]{row, col});
                                    alreadyAdded.add(val);
                                } else if (unique.contains(val)) {
                                    unique.remove(new Integer(val));
                                    own.remove(val);
                                }
                            }
                        }
                    }
                }

                if (!unique.isEmpty()) {
                    for (int value : unique) {
                        int[] coord = own.get(value);
                        int row = coord[0];
                        int col = coord[1];
                        puzzle[row][col] = value;

                        return new int[]{row, col, value, 3};
                    }
                }
            }
        }

        return null;
    }
}