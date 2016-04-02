package operators;

import com.google.common.collect.Table;
import model.Individual;
import model.Population;
import model.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Sandra on 2016-03-14.
 */
public class MinColorDistanceFitness implements IFitness {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinColorDistanceFitness.class);


    public String showValues(Table.Cell<Integer, Integer, Integer> edge, Map<Integer, Vertex> vertices) {
        return vertices.get(edge.getRowKey()).getWeight() + "\t" + vertices.get(edge.getColumnKey()).getWeight() + "\t" + edge.getValue();
    }

    @Override
    public int fitness(Individual individual) {
        //  int result = 0;
        int maxVertexColor = individual.findMaxColor();
        int minVertexColor = individual.findMinColor();
        int result = (maxVertexColor - minVertexColor + 1) * (individual.incorrectEdges() + 1);
        individual.setFitness(result);
//            LOGGER.info("Total distance: "+result);
        return result;
    }

    @Override
    public int fitness(Population pop) {
        for (Individual ind : pop.getMembers()) {
            int maxVertexColor = ind.findMaxColor();
            int minVertexColor = ind.findMinColor();
            int result = (maxVertexColor - minVertexColor + 1) * (ind.incorrectEdges() + 1);
            ind.setFitness(result);
        }
        return 0;

    }
}
