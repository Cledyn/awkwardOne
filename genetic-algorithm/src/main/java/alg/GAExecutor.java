package alg;

import model.Individual;
import model.Population;
import operators.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.Parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sandra on 2016-03-12.
 */
public class GAExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(GAExecutor.class);
    private ISelection selection;
    private ICrossover crossover;
    private IMutation mutation;
    private int populationSize;
    private int maxColorNo;
    private IFitness fitness;

    public GAExecutor(ISelection selection, ICrossover crossover, IMutation mutation, IFitness fitness, int populationSize, int maxColorNo) {
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = mutation;
        this.populationSize = populationSize;
        this.maxColorNo = maxColorNo;
        this.fitness = fitness;
    }

    //todo: zmienić na initPopSize i potem z nich w drodze selekcji wybierać 100 najlepszych, potem do krzyżowania i mutacji?
    public Population initPopulation(String fileName) throws IOException {
        Random randGen = new Random();
        List<Individual> samplePopulation = new ArrayList<Individual>();

        int counter = 0;
        while (counter < 200) {
            Individual sampleIndividual = Parser.getEdges(fileName);
            Individual newOne = Individual.copyGraphAndSetVertexWeight(this.maxColorNo, sampleIndividual, randGen);
            samplePopulation.add(newOne);
            counter++;
        }
        Population generation = new Population(samplePopulation);
        return generation;
    }

    public Population execute(Population parents, double percentagePopulationToSelect) {
        fitness.fitness(parents);
        Population newGeneration = doPopSelection(parents, percentagePopulationToSelect);
        List<Individual> individualsToCrossover = doSelection(newGeneration,0.2);
        Population afterCrossover = crossover.crossover(individualsToCrossover);
        newGeneration.addMembers(mutation.mutate(afterCrossover));
        return newGeneration;
    }

    public int runGA(int noOfGeneration, double procPopToSelect, String fileName, String saveFileName) throws IOException {
        long startTimer = System.nanoTime();
        Population ancestors = initPopulation(fileName);
        int currentMaxColorInPop = Integer.MAX_VALUE;
        int iterationNo = 0;
        fitness.fitness(ancestors);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(saveFileName, true)));
        pw.println("iteracja, najlepszy, sredni, najgorszy");
        while (iterationNo < noOfGeneration) {
            Individual[] mems = ancestors.getNeededMembers();

            pw.println(String.format(iterationNo + ", " + mems[0].getFitness() + ", " + mems[1].getFitness()));
            Population nextGeneration = execute(ancestors, procPopToSelect);

            ancestors = nextGeneration;
            iterationNo++;
        }
        long elapsed = (System.nanoTime() - startTimer);
        pw.println(elapsed);
        pw.flush();
        pw.close();
        return ancestors.getBestMember().findMaxColor();
    }


    public List<Individual> doSelection(Population population, double popPercentageToSelect) {

        return selection.select(population, (int) (population.getSize() * popPercentageToSelect), "");
    }

    public Population doPopSelection(Population population, double popPercentageToSelect) {

        return new Population(selection.select(population, (int) (population.getSize() * popPercentageToSelect), ""));
    }

    public static void main(String[] args) throws IOException {
        IFitness fitnessClass = new MinColorDistanceFitness();
        ISelection selectionA = new RouluetteWheelISelection(fitnessClass, Double.MIN_VALUE, 45);
        IMutation mutation = new Mutation(0.05);
        ICrossover crossover = new Crossover(0.9);
        GAExecutor exec = new GAExecutor(selectionA, crossover, mutation, fitnessClass, 100, 45);
        // Population ancestors = exec.initPopulation("GEOM30.col");

        int res = exec.runGA(100, 0.5, "GEOM80.col", "C:\\Users\\Sandra\\Desktop\\SI_Lab1\\wykresy\\results1.csv");
        // LOGGER.info("MAXX: " + res);

    }
}
