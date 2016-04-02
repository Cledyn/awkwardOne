package operators;

import com.google.common.base.Preconditions;
import model.Individual;
import model.Population;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sandra on 2016-03-14.
 */
public class Crossover implements ICrossover {

    private double crossoverProbability;

    public Crossover(double crossoverProbability) {
        Preconditions.checkArgument(crossoverProbability >= 0 && crossoverProbability <= 1, "Incorrect argument");
        this.crossoverProbability = crossoverProbability;
    }


    @Override
    public Population crossover(Population population) {
        int crossIndex = (int) population.getSize() / 2;
        int crossInd = population.individualSize();
        for (int i = 0; i < crossIndex; i++) {
            if (ThreadLocalRandom.current().nextDouble(0, 1) <= crossoverProbability) {
                Individual mom = population.getMember(i);
                Individual dad = population.getMember(crossIndex + i);
                population.addIndividual(replaceVerticesAfter(crossInd, mom, dad));
            }

        }
        return population;
    }

    @Override
    public Population crossover(List<Individual> individualList) {
        Population afterCrossover = new Population();
        int crossIndex = (int)individualList.size() / 2;
        for(Individual ind: individualList){
          int toCrossover =   ThreadLocalRandom.current().nextInt(0, individualList.size());
            if(ThreadLocalRandom.current().nextDouble(0, 1) <= crossoverProbability){
                afterCrossover.addIndividual(replaceVerticesAfter(crossIndex,ind, individualList.get(toCrossover)));
            }
        }
        return afterCrossover;
    }

    public Individual replaceVerticesAfter(int vertexNo, Individual mom, Individual dad) {
        Individual child = new Individual(mom);
        for (int i = vertexNo; i < dad.getVertices().size(); i++) {
            child.replaceVertex(i, dad);
        }
        return child;
    }

}
