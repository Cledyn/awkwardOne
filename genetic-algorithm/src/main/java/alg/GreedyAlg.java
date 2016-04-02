package alg;

import com.google.common.collect.Table;
import model.Individual;
import model.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.Parser;

import java.io.IOException;
import java.util.Map;

public class GreedyAlg {

    public GreedyAlg() {
    }

    private Individual individual;
    private boolean[] colouredIndex;

    private static final Logger LOGGER = LoggerFactory.getLogger(GreedyAlg.class);

    public void colorGraph(String fileName) throws IOException {
        this.individual = Parser.getEdges(fileName);
        this.colouredIndex = new boolean[individual.getGraphSize() + 1];
        //initial
        individual.changeVertexColor(1, 1);
        int colors = (int) 1.5 * individual.getGraphSize();
        greedyColor(colors);
        LOGGER.info("Show max color:" + individual.findMaxColor());

    }

    public void greedyColor(int rangeColor) {
        for (Table.Cell<Integer, Integer, Integer> edge : individual.getEdges()) {
            colorAdjacency(edge.getRowKey(), rangeColor);
            colorAdjacency(edge.getColumnKey(), rangeColor);
            int edgeWeight = edge.getValue();
            LOGGER.info("" + edgeWeight);
            int oneEnd = edge.getRowKey();
            int sndEnd = edge.getColumnKey();
            int diff = oneEnd - edgeWeight;
            if (diff < 0 && diff >= -rangeColor) {
                individual.changeVertexColor(sndEnd, -diff);
            }
            else if(diff >= -rangeColor){}
            else {
                individual.changeVertexColor(sndEnd, diff);
            }
        }
    }

    public void colorAdjacency(int vertexIndex, int colorRange) {

        this.colouredIndex[vertexIndex] = true;
        for (Map.Entry<Integer, Vertex> adjacent : individual.getVertexByNumber(vertexIndex).getAdjacency().entrySet()) {
            int edgeVal = individual.getEdge(adjacent.getKey(), vertexIndex);
            int currColor = individual.getVertexByNumber(vertexIndex).getWeight();
            if (!colouredIndex[adjacent.getKey()]) {
                if (currColor >= edgeVal) {
                    if (currColor - edgeVal <= colorRange)
                        adjacent.getValue().setColor(currColor - edgeVal);

                } else {
                    if (edgeVal - currColor <= colorRange)
                        adjacent.getValue().setColor(edgeVal - currColor);
                }
                this.colouredIndex[adjacent.getKey()] = true;
            }
        }
    }

    public boolean isEdgeValid(Table.Cell<Integer, Integer, Integer> edge) {
        return edge.getValue() < Math.abs(edge.getRowKey() - edge.getColumnKey());

    }


    public static void main(String[] args) throws IOException {
        GreedyAlg a = new GreedyAlg();
        a.colorGraph("GEOM40.col");
    }
}
