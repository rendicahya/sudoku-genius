package rc.sudokugenius.algorithm.genetic;

import java.util.Arrays;
import rc.collections.ArrayList;

class Population {

    private Individual[] parent;
    private Individual[] child;
    private int totalFitness;
    private boolean totalFitnessCalculated;
    private ArrayList<Individual> parentList;

    Population() {
        totalFitnessCalculated = false;
        parentList = new ArrayList<Individual>();
    }

    private void calculateTotalFitness() {
        totalFitness = 0;

        for (Individual p : parent) {
            totalFitness += p.getFitness();
        }

        totalFitnessCalculated = true;
    }

    void randomizeParentsGenes() {
        for (Individual p : parent) {
            p.randomizeGenes();
        }

        totalFitnessCalculated = false;
    }

    Individual[] getElite() {
        double fittest1 = 0;
        double fittest2 = 0;
        int fittestId1 = 0;
        int fittestId2 = 0;

        for (int p = 0; p < parent.length; p++) {
            final int fitness = parent[p].getFitness();

            if (fitness > fittest1) {
                fittest2 = fittest1;
                fittestId2 = fittestId1;

                fittest1 = fitness;
                fittestId1 = p;
            } else if (fitness > fittest2) {
                fittest2 = fitness;
                fittestId2 = p;
            }
        }

        return new Individual[]{parent[fittestId1], parent[fittestId2]};
    }

    Individual[] rouletteWheelUnique() {
        double prev = 0;
        int parentListSize = parentList.size();
        double[] individualChance = new double[parentListSize];
        int listTotalFitness = getListTotalFitness();
        Individual[] offspring = new Individual[2];

        if (!totalFitnessCalculated) {
            calculateTotalFitness();
        }

        for (int p = 0; p < parentListSize; p++) {
            prev = individualChance[p] = prev + ((double) parentList.get(p).getFitness() / listTotalFitness);
        }

        int parent1 = 0;
        double random = Math.random();

        for (int p = 0; p < parentListSize; p++) {
            if (random < individualChance[p]) {
                parent1 = p;

                break;
            }
        }

        offspring[0] = parentList.remove(parent1);

        prev = 0;
        parentListSize = parentList.size();
        individualChance = new double[parentListSize];
        listTotalFitness = getListTotalFitness();

        for (int p = 0; p < parentListSize; p++) {
            prev = individualChance[p] = prev + ((double) parentList.get(p).getFitness() / listTotalFitness);
        }

        int parent2 = 0;
        random = Math.random();

        for (int p = 0; p < parentListSize; p++) {
            if (random < individualChance[p]) {
                parent2 = p;

                break;
            }
        }

        offspring[1] = parentList.remove(parent2);

        return offspring;
    }

    Individual[] rouletteWheel() {
        double prev = 0;
        double[] individualChance = new double[parent.length];

        if (!totalFitnessCalculated) {
            calculateTotalFitness();
        }

        for (int p = 0; p < parent.length; p++) {
            prev = individualChance[p] = prev + ((double) parent[p].getFitness() / totalFitness);
        }

        int parent1 = 0;
        double random = Math.random();

        for (int p = 0; p < parent.length; p++) {
            if (random < individualChance[p]) {
                parent1 = p;

                break;
            }
        }

        int parent2 = 0;
        random = Math.random();

        for (int p = 0; p < parent.length; p++) {
            if (random < individualChance[p]) {
                parent2 = p;

                break;
            }
        }

        return new Individual[]{parent[parent1], parent[parent2]};
    }

    void nextGeneration() {
        for (int p = 0; p < parent.length; p++) {
            parent[p].copyChromosome(child[p]);
        }

        totalFitnessCalculated = false;
    }

    void initParentList() {
        parentList.removeAll(parentList);
        parentList.addAll(Arrays.asList(parent));
    }

    void setChild(Individual[] child) {
        this.child = child;
    }

    void setParent(Individual[] parent) {
        this.parent = parent;

        totalFitnessCalculated = false;
    }

    int getTotalFitness() {
        if (!totalFitnessCalculated) {
            calculateTotalFitness();
        }

        return totalFitness;
    }

    private int getListTotalFitness() {
        int sum = 0;

        for (Individual i : parentList) {
            sum += i.getFitness();
        }

        return sum;
    }
}
