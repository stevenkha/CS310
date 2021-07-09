import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Comparator;

public class GenAlg {
    private final List<List<Integer>> matrix;
    private final List<String> labels;
    private int mutations;

    public GenAlg(List<List<Integer>> matrix, List<String> labels) {
        this.matrix = matrix;
        this.labels = labels;
        this.mutations = 0;
    }

    //outputs information about each crossover and what the "best" path is at the end
    public List<String> shortPath(int popSize, int epochs) {
        List<List<String>> pop = initialPop(popSize);
        pop.sort(sortByFitness);
        for (int i = 0; i < epochs; i++) {
            int avgFitness = 0;
            pop = crossover(pop);
            pop.sort(sortByFitness);

            for (List<String> n : pop) {
                avgFitness += calcFitness(n);
            }

            System.out.println("Generation " + (i + 1));
            System.out.println("Mutations: " + this.mutations);
            System.out.println("Average Fitness: " + (avgFitness / popSize));
            System.out.println("Best Fitness: " + calcFitness(pop.get(0)));
            System.out.println("--------------------------------");
        }

        System.out.println("--------------------------------");
        System.out.println("Final Results:");
        System.out.println("Total population size: " + popSize);
        System.out.println("Total Number of epochs: " + epochs);
        System.out.println("Cost of short path: " + calcFitness(pop.get(0)));
        System.out.print("Path: ");
        return pop.get(0);
    }

    //method creates a randomly generated set of "chromosomes" or sequences for the nodes/cities to visit and adds them into a list
    private List<List<String>> initialPop(int popSize) {
        List<List<String>> parents = new ArrayList<>();
        for (int i = popSize; i > 0; i--) {
            parents.add(randPopGen(this.labels.size()));
        }

        return parents;
    }

    //comparator sorts list of chromosomes in order by their cost
    private final Comparator<List<String>> sortByFitness = Comparator.comparingInt(this::calcFitness);
    //method calculates the fitness of each chromosome
    private int calcFitness(List<String> chromosomes) {
        int sum = 0;
        //for every string in chromosome strand, calculate the cost of travel for the sequence
        for (int i = 0; i < chromosomes.size(); i++) {
            //get the first "label/city"
            int firstNodeIndex = this.labels.indexOf(chromosomes.get(i));
            int nextNode;
            //if on the last node, make the next node the first node, otherwise make it the next node
            if (i == chromosomes.size() - 1) {
                nextNode = this.labels.indexOf(chromosomes.get(0));
            } else {
                nextNode = this.labels.indexOf(chromosomes.get(i + 1));
            }

            //find corresponding label's list of cost and add the correct cost to the sum
            sum += this.matrix.get(firstNodeIndex).get(nextNode);
        }

        return sum;
    }

    //helper method that randomizes the set of node sequences
    private List<String> randPopGen(int length) {
        Random rand = new Random();
        List<String> genome = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            genome.add(this.labels.get(i));
        }

        while (length > 1) {
            String lastLabel = genome.get(length - 1);
            int x = rand.nextInt(length - 1);
            genome.set(length - 1, genome.get(x));
            genome.set(x, lastLabel);
            length--;
        }

        return genome;
    }

    //method performs a "crossover" step on the population
    private List<List<String>> crossover(List<List<String>> pop) {
        this.mutations = 0;
        int chromosomeSize = pop.get(0).size();
        List<List<String>> newPop = new ArrayList<>();
        //add the first two best chromosomes and their crossover to the new population list
        newPop.add(pop.get(0));
        newPop.add(pop.get(1));
        newPop.add(breed(pop.get(0), pop.get(1)));
        //do crossovers with the best chromosome and the best half of the population
        int j = 2;
        int x = pop.size() / 2;
        for (int i = 0; i < (pop.size() / 2) - 1; i++) {
            newPop.add(breed(pop.get(0), pop.get(j)));
            j++;
        }

        //do crossovers with the second best chromosome and the "worst" half of the population
        for (int i = 0; i < (pop.size() / 2) - 2; i++) {
            newPop.add(breed(pop.get(1), pop.get(x)));
        }

        //% chance to perform a mutation if applicable
        for (List<String> ignored : pop) {
            if (randNum(1, pop.size()) == randNum(1, pop.size())) {
                mutate(chromosomeSize, pop);
                this.mutations++;
            }
        }

        return newPop;
    }

    //method breeds the two chromosomes by swapping positions
    private List<String> breed(List<String> par1, List<String> par2) {
        List<String> newPar = new ArrayList<>(par2);
        for (int i = 0; i < (par1.size() / 2) - 1; i++) {
            int x = randNum(0,(par1.size() / 2) - 1);
            String toSwap = par1.get(x);
            newPar.set(newPar.indexOf(toSwap), newPar.get(x));
            newPar.set(x, toSwap);
        }

        return newPar;
    }

    //method implements a mutation step where it selects a random "chromosome" and two random indices to swap
    private void mutate(int chromosomeSize, List<List<String>> pop) {
        List<String> theChromosome = pop.get(randNum(0, pop.size() - 1));
        int randomIndex1 = randNum(0, chromosomeSize - 1);
        int randomIndex2 = randNum(0, chromosomeSize - 1);
        String toSwap = theChromosome.get(randomIndex1);
        theChromosome.set(randomIndex1, theChromosome.get(randomIndex2));
        theChromosome.set(randomIndex2, toSwap);
    }

    private int randNum(int lowerBound, int upperBound) {
        Random rand = new Random();
        return rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }

    //prints out the matrix
    @Override
    public String toString() {
        StringBuilder labelsWithCost = new StringBuilder();
        for (String n : this.labels) {
            labelsWithCost.append(n).append("\t");
        }

        System.out.print("  " + labelsWithCost + " " + "\n");
        for (int i = 0; i < this.matrix.size(); i++) {
            StringBuilder values = new StringBuilder();
            for (Integer n : this.matrix.get(i)) {
                values.append(n).append("\t");
            }

            System.out.println(this.labels.get(i) + "|" + values);
        }

        return "";
    }
}
