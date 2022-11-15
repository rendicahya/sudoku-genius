package rc.sudokugenius.algorithm.genetic;

import rc.collections.ArrayList;
import rc.sudokugenius.collections.HashMap;

class GeneticOperation {

    static void crossover(Individual indv1, Individual indv2, int point) {
        final int length = indv1.getChromosome().length - point;
        int[] tmp = new int[length];

        System.arraycopy(indv1.getChromosome(), point, tmp, 0, length);
        System.arraycopy(indv2.getChromosome(), point, indv1.getChromosome(), point, length);
        System.arraycopy(tmp, 0, indv2.getChromosome(), point, length);
    }

    static boolean mutation(Individual indv, ArrayList<Integer> mutableGenes, HashMap<Integer, ArrayList<Integer>> mutableMap) {
        final int mutationPoint = mutableGenes.getRandom();
        final int newGene = mutableMap.get(mutationPoint).getRandom();

        indv.setGeneAt(mutationPoint, newGene);

        return indv.getFitness() == indv.getChromosome().length;
    }

    static boolean mutationOpt(Individual indv, ArrayList<Integer> mutableGenes, HashMap<Integer, ArrayList<Integer>> mutableMap) {
        int lastFitness;
        boolean giveup = false;
        Individual tmpIndv = null;
        ArrayList<Integer> copyofMutableGenes = mutableGenes.clone();
        HashMap<Integer, ArrayList<Integer>> copyofMutableMap = mutableMap.clone();

        do {
            tmpIndv = indv.clone();
            lastFitness = tmpIndv.getFitness();

            final int mutationPoint = copyofMutableGenes.getRandom();

            ArrayList<Integer> option = copyofMutableMap.get(mutationPoint);
            option.remove(new Integer(tmpIndv.getGeneAt(mutationPoint)));

            final int newGene = option.getRandomAndRemove();

            if (option.isEmpty()) {
                copyofMutableGenes.remove(new Integer(mutationPoint));

                if (copyofMutableGenes.isEmpty()) {
                    giveup = true;

                    break;
                }
            }

            tmpIndv.setGeneAt(mutationPoint, newGene);
        } while (tmpIndv.getFitness() < lastFitness);

        if (!giveup) {
            indv.setChromosome(tmpIndv.getChromosome());
        }

        return indv.getFitness() == indv.getChromosome().length;
    }
}