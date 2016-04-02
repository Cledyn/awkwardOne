package operators;

import model.Individual;
import model.Population;

/**
 * Created by Sandra on 2016-03-13.
 */
public interface IFitness {

   public int fitness(Individual individual);
    public int fitness(Population population);
}
