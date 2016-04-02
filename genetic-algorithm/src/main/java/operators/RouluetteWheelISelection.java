package operators;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import model.Individual;
import model.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sandra on 2016-03-11.
 */
public class RouluetteWheelISelection implements ISelection {


    private static final Logger LOGGER = LoggerFactory.getLogger(RouluetteWheelISelection.class);;
    public static final double MIN_FITNESS_LIMIT = -Double.MAX_VALUE;
    private Random randomGenerator;
    private IFitness fitnessClass;
    private int maxColorValue;

    private double minFitness = MIN_FITNESS_LIMIT;


    public RouluetteWheelISelection(IFitness fitnessClass, double minFitness, int maxColorValue) {
        setMinFitness(minFitness, maxColorValue);
        this.fitnessClass = fitnessClass;
        randomGenerator = new Random();
    }

    public double getMinFitness() {
        return this.minFitness;
    }

    public void setMinFitness(double minFitness, int maxColorValue) {
        if (Double.isNaN(minFitness)
                || Double.NEGATIVE_INFINITY == minFitness
                || Double.POSITIVE_INFINITY == minFitness)
            throw new IllegalArgumentException("Minumum fitness is an illegal "
                    + "value or larger then maximum ");
        this.minFitness = minFitness;
        this.maxColorValue = maxColorValue;
    }


    @Override
    public Individual select(Population population, String params) {
        double cumSum = calculateCumulativeFitness(population);
        return spinRoulette(cumSum, population, params);
    }


    @Override
    public List<Individual> select(Population population, int howMany, String params) {
        Preconditions.checkArgument(population.getSize() > howMany, "Za mała populacja.");
        double cumSum = calculateCumulativeFitness(population);
        List<Individual> selected = Lists.newArrayList();
        for (int i = 0; i < howMany; i++) {
            Individual ind = spinRoulette(cumSum, population, params);
            selected.add(ind);
        }
        return selected;
    }

    private double calculateCumulativeFitness(Population population) {
        double sum = 0;
        for (int i = 0; i < population.getSize(); i++) {
            double fitVal = getRelativeFitnessValue(population.getMember(i));
            sum += fitVal;
        }
        if (sum == Double.POSITIVE_INFINITY || Double.isNaN(sum))
            throw new IllegalStateException("\nSkumulowana funkcja celu " + sum
                    + " jest poza zakresem. ");
        return sum;
    }

    private Individual spinRoulette(double cumSum, Population pop, String params) {

        int popSize = pop.getSize();

        if (0 == cumSum)
            return pop.getMember((int) randomGenerator.nextInt(popSize));

        double roulette = ThreadLocalRandom.current().nextInt(0, (int) cumSum);
        double lowRange = 0.0;
        double highRange = 0.0;
        for (Individual indv : pop.getMembers()) {
            double fval = getRelativeFitnessValue(indv);
            indv.setFitness(fval);
            highRange += fval;
            if (lowRange <= roulette && roulette < highRange)
                return indv;
            lowRange = highRange;
        }

        throw new Error("It shouldn't get executed!");
    }

    //todo: refactor relative fitness value
    private double getRelativeFitnessValue(Individual indv) {
        double fitnessValue = fitnessClass.fitness(indv);
        double fitnessValueForRoulette = Math.pow(fitnessValue,-1);
        return fitnessValue;
    }

}
