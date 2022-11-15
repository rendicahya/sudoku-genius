package rc.sudokugenius.algorithm.genetic;

import java.util.Arrays;
import rc.collections.ArrayList;

public class Individual {

    private int fitness;
    private int[] chromosome;
    private int[][] puzzle;
    private ArrayList<Integer>[] candidates;
    private boolean fitnessCalculated = false;

    void randomizeGenes() {
        for (int i = 0; i < chromosome.length; i++) {
            chromosome[i] = candidates[i].getRandom();
        }
    }

    private void calculateFitness() {
        int invalid = 0;
        int[][] values = new int[9][9];

        for (int row = 0, chr = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                values[row][col] = puzzle[row][col] == 0 ? chromosome[chr++] : puzzle[row][col];
            }
        }

        for (int row = 0; row < 9; row++) {
            cell:
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0) {
                    final int cellValue = values[row][col];

                    for (int i = 0; i < 9; i++) {
                        if (i != col && values[row][i] == cellValue) {
                            invalid++;

                            continue cell;
                        }

                        if (i != row && values[i][col] == cellValue) {
                            invalid++;

                            continue cell;
                        }
                    }

                    int colStart = col < 3 ? 0 : col < 6 ? 3 : 6;
                    int colEnd = col < 3 ? 3 : col < 6 ? 6 : 9;
                    int rowStart = row < 3 ? 0 : row < 6 ? 3 : 6;
                    int rowEnd = row < 3 ? 3 : row < 6 ? 6 : 9;

                    for (int r = rowStart; r < rowEnd; r++) {
                        for (int c = colStart; c < colEnd; c++) {
                            if (r != row && c != col && values[r][c] == cellValue) {
                                invalid++;

                                continue cell;
                            }
                        }
                    }
                }
            }
        }

        fitnessCalculated = true;
        fitness = chromosome.length - invalid;
    }

    public boolean hasError() {
        int[][] values = new int[9][9];

        for (int row = 0, chr = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                values[row][col] = puzzle[row][col] == 0 ? chromosome[chr++] : puzzle[row][col];
            }
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0) {
                    final int cellValue = values[row][col];

                    for (int i = 0; i < 9; i++) {
                        /**
                         * Scan row
                         */
                        if (i != col && values[row][i] == cellValue) {
                            return true;
                        }

                        /**
                         * Scan column
                         */
                        if (i != row && values[i][col] == cellValue) {
                            return true;
                        }
                    }

                    int colStart = col < 3 ? 0 : col < 6 ? 3 : 6;
                    int colEnd = col < 3 ? 3 : col < 6 ? 6 : 9;
                    int rowStart = row < 3 ? 0 : row < 6 ? 3 : 6;
                    int rowEnd = row < 3 ? 3 : row < 6 ? 6 : 9;

                    for (int r = rowStart; r < rowEnd; r++) {
                        for (int c = colStart; c < colEnd; c++) {
                            if (r != row && c != col && values[r][c] == cellValue) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public Individual clone() {
        Individual clone = new Individual();

        clone.setChromosome(Arrays.copyOf(chromosome, chromosome.length));
        clone.setFitness(fitness);
        clone.setCandidates(candidates);
        clone.setPuzzle(puzzle);

        return clone;
    }

    void copyChromosome(Individual src) {
        setChromosome(Arrays.copyOf(src.getChromosome(), src.getChromosome().length));
    }

    void initChromosome(int chromosomeLength) {
        chromosome = new int[chromosomeLength];
    }

    int getGeneAt(int locus) {
        return chromosome[locus];
    }

    void setGeneAt(int locus, int value) {
        fitnessCalculated = false;
        chromosome[locus] = value;
    }

    void setCandidates(ArrayList<Integer>[] candidates) {
        this.candidates = candidates;
    }

    public void setChromosome(int[] chromosome) {
        fitnessCalculated = false;
        this.chromosome = chromosome;
    }

    int[] getChromosome() {
        return chromosome;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    void setFitness(int fitness) {
        this.fitness = fitness;
    }

    int getFitness() {
        if (!fitnessCalculated) {
            calculateFitness();
        }

        return fitness;
    }
}