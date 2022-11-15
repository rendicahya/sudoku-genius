package rc.sudokugenius.algorithm;

import rc.collections.ArrayList;
import rc.sudokugenius.algorithm.genetic.Individual;

public class BruteForce {

    private ArrayList<Integer>[] candidates;

    public BruteForce setCandidates(ArrayList<Integer>[] candidates) {
        this.candidates = candidates;

        return this;
    }

    public int[] attack(int[][] puzzle) {
        final int length = candidates.length;
        int[] solution = new int[length];
        int[] pos = new int[length];
        boolean finish = false;

        Individual indv = new Individual();
        indv.setPuzzle(puzzle);

        do {
            for (int i = 0; i < length; i++) {
                solution[i] = candidates[i].get(pos[i]);
            }

            indv.setChromosome(solution);

            if (!indv.hasError()) {
                break;
            }

            for (int i = length - 1; i >= 0; i--) {
                if (pos[i] < candidates[i].size() - 1) {
                    pos[i]++;

                    for (int j = i + 1; j < length; j++) {
                        pos[j] = 0;
                    }

                    break;
                } else if (i == 0 && pos[i] == candidates[i].size() - 1) {
                    finish = true;

                    break;
                }
            }
        } while (!finish);

        return solution;
    }
}