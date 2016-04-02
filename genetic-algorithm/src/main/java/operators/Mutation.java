package operators;

import model.Individual;
import model.Population;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sandra on 2016-03-14.
 */
public class Mutation implements IMutation {


    public Mutation(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    private double mutationProbability;

    @Override
    public Population mutate(Population parents) {

        for (Individual individual : parents.getMembers()) {
            if (ThreadLocalRandom.current().nextDouble(0,1) <= mutationProbability) {
                mutateParentToGetChild(individual);
            }
        }
        return parents;
    }

    public void mutateParentToGetChild(Individual parent) {

        int noOfVertices = parent.getVertices().size();
        int colorRange = parent.findMaxColor();
        int vertexToChange = ThreadLocalRandom.current().nextInt(1, noOfVertices + 1);
        int newColor = ThreadLocalRandom.current().nextInt((colorRange-noOfVertices)+1)+noOfVertices;
        parent.changeVertexColor(vertexToChange, newColor);

    }

}
