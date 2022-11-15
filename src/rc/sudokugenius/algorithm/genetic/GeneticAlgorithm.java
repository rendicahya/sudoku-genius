package rc.sudokugenius.algorithm.genetic;

import org.jfree.data.xy.XYSeries;
import rc.collections.ArrayList;
import rc.sudokugenius.collections.HashMap;

public class GeneticAlgorithm {

    private int populationSize;
    private int maxGenerations;
    private int maxAttempt;
    private double crossoverRate;
    private int crossoverPoints;
    private double mutationRate;
    private int chromosomeLength;
    private ArrayList<Integer>[] candidates;
    private ArrayList<Integer> mutableGenes;
    private HashMap<Integer, ArrayList<Integer>> mutableMap = new HashMap<Integer, ArrayList<Integer>>();
    private int[][] puzzle;
    private XYSeries series = new XYSeries("Genetic Algorithm");

    public GeneticAlgorithm setPopulationSize(int populationSize) {
        this.populationSize = populationSize;

        return this;
    }

    public GeneticAlgorithm setMaxGenerations(int maxGenerations) {
        this.maxGenerations = maxGenerations;

        return this;
    }

    public GeneticAlgorithm setMaxAttempt(int maxAttempt) {
        this.maxAttempt = maxAttempt;

        return this;
    }

    public GeneticAlgorithm setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;

        return this;
    }

    public GeneticAlgorithm setCrossoverPoints(int crossoverPoints) {
        this.crossoverPoints = crossoverPoints;

        return this;
    }

    public GeneticAlgorithm setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;

        return this;
    }

    public GeneticAlgorithm setChromosomeLength(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;

        return this;
    }

    public GeneticAlgorithm setCandidates(ArrayList<Integer>[] candidates) {
        this.candidates = candidates;

        return this;
    }

    public GeneticAlgorithm setMutableGenes(ArrayList<Integer> mutableGenes) {
        this.mutableGenes = mutableGenes;

        return this;
    }

    public GeneticAlgorithm setMutableMap(HashMap<Integer, ArrayList<Integer>> mutable) {
        this.mutableMap = mutable;

        return this;
    }

    public GeneticAlgorithm setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;

        return this;
    }

    public XYSeries getSeries() {
        return series;
    }

    public int[] evolve(boolean optimizedEvolution) {
        Individual[] parent = new Individual[populationSize];
        Individual[] child = new Individual[populationSize];
        int attempt = 0;
        int[] solution = null;
        int counter = 0;

        Population population = new Population();
        population.setParent(parent);
        population.setChild(child);

        /**
         * INIT POPULATION
         */
        for (int i = 0; i < populationSize; i++) {
            parent[i] = new Individual();
            parent[i].initChromosome(chromosomeLength);
            parent[i].setCandidates(candidates);
            parent[i].setPuzzle(puzzle);

            child[i] = new Individual();
            child[i].initChromosome(chromosomeLength);
            child[i].setCandidates(candidates);
            child[i].setPuzzle(puzzle);
        }

        main:
        do {
            population.randomizeParentsGenes();

            /**
             * MAIN LOOP
             */
            for (int generation = 0; maxGenerations == -1 || generation < maxGenerations; generation++) {
                for (Individual p : parent) {
                    if (p.getFitness() == chromosomeLength) {
                        solution = p.getChromosome();

                        break main;
                    }
                }

                /**
                 * ELITISM
                 */
                Individual[] fittest = population.getElite();

                child[0].copyChromosome(fittest[0]);
                child[1].copyChromosome(fittest[1]);

                series.add(counter, fittest[0].getFitness());

//                if (counter > (attempt * maxGenerations) + 98
//                        && series.getY(counter).intValue() == series.getY(counter - 99).intValue()) {
//                    break;
//                }

                counter++;

                population.initParentList();

                for (int c = 2, d = 3; c < populationSize; c += 2, d += 2) {
                    Individual[] chosenParent = population.rouletteWheelUnique();

                    /**
                     * Make children
                     */
                    child[c].copyChromosome(chosenParent[0]);
                    child[d].copyChromosome(chosenParent[1]);

                    if (crossoverRate == 1 || Math.random() < crossoverRate) {
                        for (int i = 0; i < crossoverPoints; i++) {
                            final int xoverPoint = (int) Math.round(Math.random() * (chromosomeLength - 1));
                            GeneticOperation.crossover(child[c], child[d], xoverPoint);
                        }
                    }

                    if (mutationRate == 1 || Math.random() < mutationRate) {
                        if (optimizedEvolution) {
                            if (GeneticOperation.mutationOpt(child[c], mutableGenes, mutableMap)) {
                                solution = child[c].getChromosome();

                                break main;
                            } else if (GeneticOperation.mutationOpt(child[d], mutableGenes, mutableMap)) {
                                solution = child[d].getChromosome();

                                break main;
                            }
                        } else {
                            if (GeneticOperation.mutation(child[c], mutableGenes, mutableMap)) {
                                solution = child[c].getChromosome();

                                break main;
                            } else if (GeneticOperation.mutation(child[d], mutableGenes, mutableMap)) {
                                solution = child[d].getChromosome();

                                break main;
                            }
                        }
                    }
                }

                population.nextGeneration();
            }
        } while (maxAttempt == -1 || attempt++ < maxAttempt);

        return solution;
    }
}
