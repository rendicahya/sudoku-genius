package rc.sudokugenius.sudoku.operations;

import rc.collections.ArrayList;
import rc.sudokugenius.collections.HashMap;

public class PuzzleAnalyzer {

    private int emptyCellsNum;
    private int[][] puzzle;
    private ArrayList<Integer>[] candidates;
    private ArrayList<Integer> mutableCells;
    private HashMap<Integer, ArrayList<Integer>> mutableMap;
    private boolean analyzed;

    public PuzzleAnalyzer() {
        analyzed = false;
        mutableCells = new ArrayList<Integer>();
        mutableMap = new HashMap<Integer, ArrayList<Integer>>();
    }

    public void analyze() {
        emptyCellsNum = 0;

        for (int[] row : puzzle) {
            for (int col : row) {
                if (col == 0) {
                    emptyCellsNum++;
                }
            }
        }

        candidates = new ArrayList[emptyCellsNum];
        mutableCells.clear();
        mutableMap.clear();

        for (int row = 0, cell = 0, chr = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++, cell++) {
                if (puzzle[row][col] == 0) {
                    candidates[chr] = CellCandidatesFinder.find(puzzle, row, col);

                    if (candidates[chr].size() > 1) {
                        mutableCells.add(chr);

                        mutableMap.put(chr, candidates[chr]);
                    }

                    chr++;
                }
            }
        }

        analyzed = true;
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;

        analyzed = false;
    }

    public int getEmptyCellsNum() {
        if (!analyzed) {
            analyze();
        }

        return emptyCellsNum;
    }

    public ArrayList<Integer>[] getCandidates() {
        if (!analyzed) {
            analyze();
        }

        return candidates;
    }

    public ArrayList<Integer> getMutableCells() {
        if (!analyzed) {
            analyze();
        }

        return mutableCells;
    }

    public HashMap<Integer, ArrayList<Integer>> getMutableMap() {
        if (!analyzed) {
            analyze();
        }

        return mutableMap;
    }
}