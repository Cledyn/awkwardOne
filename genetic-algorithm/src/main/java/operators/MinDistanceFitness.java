package operators;

import com.google.common.collect.Table;
import model.Individual;
import model.Population;
import model.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Created by Sandra on 2016-03-08.
 */
public class MinDistanceFitness implements IFitness {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinDistanceFitness.class);
    //jedno "kryterium"
    public  int calculateDistanceBetween(Table.Cell<Integer, Integer, Integer> edge,Map<Integer, Vertex> vertices) {

        //Map<Integer,Vertex> vertices
        int v1Color= vertices.get(edge.getRowKey()).getWeight();
        int v2Color= vertices.get(edge.getColumnKey()).getWeight();
        int distance = Math.abs(v1Color - v2Color) - edge.getValue();
        if (distance < 0) {
            distance *= (-3);
        }
        return distance;
    }

    public String showValues(Table.Cell<Integer, Integer, Integer> edge,Map<Integer, Vertex> vertices){
        return vertices.get(edge.getRowKey()).getWeight()+"\t"+vertices.get(edge.getColumnKey()).getWeight()+"\t"+edge.getValue();
    }

    @Override
    public int fitness(Individual individual) {
        int totalDistance = 0;
        Set<Table.Cell<Integer, Integer, Integer>> edges = individual.getEdges();
        for (Table.Cell<Integer, Integer, Integer> edge : edges) {
            int distance = calculateDistanceBetween(edge,individual.getVertices());
            totalDistance += distance;
         //  System.out.println(showValues(edge,individual.getVertices()));

        }
     //   LOGGER.info("Total distance: "+totalDistance);
        return totalDistance;
    }

    @Override
    public int fitness(Population population) {
        int total = 0;
        for(Individual individual: population.getMembers()){
            int indFitness = fitness(individual);
            individual.setFitness(indFitness);
            total+=indFitness;
        }
        return 0;
    }


    //todo: jak SPEŁNIA, to... jakąś trzeba dać 'nagrodę' zamiast karać złe osobniki
//    @Override
//    public int fitness(Individual individual) {
//        int totalDistance = 0;
//        Set<Table.Cell<Integer, Integer, Integer>> edges = individual.getEdges();
//        for (Table.Cell<Integer, Integer, Integer> edge : edges) {
//            int distance = calculateDistanceBetween(edge,individual.getVertices());
//            totalDistance += distance;
//         //  System.out.println(showValues(edge,individual.getVertices()));
//
//        }
//    //    LOGGER.info("Total distance: "+totalDistance);
//        return totalDistance;
//    }
}
