package operators;

import model.Individual;
import model.Population;

import java.util.List;

/**
 * Created by Sandra on 2016-03-11.
 */
public interface ISelection {


    public Individual select(Population population, String params);
    public List<Individual> select(Population population, int howMany, String params);
}



