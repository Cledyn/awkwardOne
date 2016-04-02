package operators;

import model.Individual;
import model.Population;

import java.util.List;

/**
 * Created by Sandra on 2016-03-11.
 */
public interface ICrossover {

    public Population crossover(Population population);
    public Population crossover(List<Individual> individualList);
}
