package model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by Sandra on 2016-03-11.
 */
public class Individual {

    private static final Logger LOGGER = LoggerFactory.getLogger(Individual.class);

    private Table<Integer, Integer, Integer> edges;
    private Map<Integer, Vertex> vertices;
    private final Random generator = new Random();
    private int verticesNo;
    private double fitness;

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getGraphSize() {
        return vertices.size();
    }

    public Individual() {
        this.edges = HashBasedTable.create();
        this.vertices = Maps.newHashMap();

    }

    public Individual(int graphSize) {
        this.verticesNo = graphSize;
        this.edges = HashBasedTable.create();
        this.vertices = Maps.newHashMap();

    }

    public Individual(Individual individual) {
        this.edges = HashBasedTable.create(individual.edges);
        this.vertices = Maps.newHashMap(individual.vertices);

    }

    public Map<Integer, Vertex> getVertices() {
        return this.vertices;
    }

    public Individual(Table<Integer, Integer, Integer> edges, Map<Integer, Vertex> vertices) {
        this.vertices = Maps.newHashMap(vertices);
        this.edges = HashBasedTable.create(edges);
    }

    public Table<Integer, Integer, Integer> getAllEdges() {
        return this.edges;
    }

    public Integer getEdge(Integer v1, Integer v2) {
        return MoreObjects.firstNonNull(edges.get(v1, v2), edges.get(v2, v1));
    }

    public int addEdge(int v1, int v2, int weight) {
        Preconditions.checkArgument(weight > 0);
        Vertex vertex1 = getOrCreateVertex(v1);
        Vertex vertex2 = getOrCreateVertex(v2);
        if (v1 != v2) {
            //  Preconditions.checkNotNull(weight);
            vertex1.addToAdjacency(vertex2);
            vertex2.addToAdjacency(vertex1);
            //  LOGGER.info("Waga krawedzi" + String.valueOf(weight));
            edges.put(v1, v2, weight);
            return edges.get(v1, v2);
        } else {
            return -1;
        }
    }

    public Vertex getOrCreateVertex(int v) {
        if (!vertices.containsKey(v)) {
            Vertex newVertex = new Vertex(v);
            vertices.put(v, newVertex);
            //   LOGGER.info("Create vertex: " + v);
            return newVertex;
        } else {
            //   LOGGER.info("Founf vertex: " + v);
            return vertices.get(v);
        }
    }

    public void colorVertices(int colorRange, Random generator) {
        int min = vertices.size() / 2;
        for (int i = 1; i < vertices.size(); i++) {
            //      LOGGER.info("COlor vertex: " + vertices.get(i).toString());

            vertices.put(i, vertices.get(i).setColor(generator.nextInt(colorRange)));
        }

    }

    public Individual colorNewGraph(int colorRange, Random generator) {
        colorVertices(colorRange, generator);
        return new Individual(this.edges, this.vertices);
    }

    public static Individual copyGraphAndSetVertexWeight(int colorRange, Individual individual, Random rand) {

        Map<Integer, Vertex> newVertices = Maps.newHashMap(individual.vertices);
        int min = individual.vertices.size() / 2;
        //newVertices.putAll(individual.vertices);
        for (int i = 1; i < newVertices.size(); i++) {

            newVertices.put(i, newVertices.get(i).setColor(rand.nextInt((colorRange))));
        }
        return new Individual(individual.edges, newVertices);


    }

    public Vertex getVertexByNumber(int index){
        return vertices.get(index);
    }
    public Set<Table.Cell<Integer, Integer, Integer>> getEdges() {
        return edges.cellSet();
    }

    public double getFitness() {
        return fitness;
    }

    public String showEdges() {
        Set<Table.Cell<Integer, Integer, Integer>> edg = getEdges();
        StringBuilder sb = new StringBuilder();
        for (Table.Cell<Integer, Integer, Integer> cell : edg) {
            sb.append("V1= " + cell.getRowKey() + " V2= " + cell.getColumnKey() + " Value= " + cell.getValue());
            sb.append("\n");
            // LOGGER.info("V1= "+cell.getRowKey()+" V2= "+cell.getColumnKey()+" Value= "+cell.getValue());
        }
        Set<Map.Entry<Integer, Vertex>> entrySet = vertices.entrySet();
        for (Map.Entry<Integer, Vertex> mapEntry : entrySet) {
            // LOGGER.info("V3= "+mapEntry.getKey()+"Value= "+mapEntry.getValue().toString());
            sb.append("V3= " + mapEntry.getKey() + "Number= " + mapEntry.getValue().getNumber() + " Weight= " + mapEntry.getValue().getWeight() + " Neighbours= " + mapEntry.getValue().toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void changeVertexColor(int vertexNo, int color) {

        vertices.put(vertexNo, vertices.get(vertexNo).setColor(color));
    }


    public int findMinColor() {
        int minColor = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Vertex> vertex : vertices.entrySet()) {
            int currentColor = vertex.getValue().getWeight();
            if (currentColor < minColor)
                minColor = currentColor;
        }
        return minColor;
    }

    public int incorrectEdges() {

        int incorrectEntriesCounter = 0;
        for (Table.Cell<Integer, Integer, Integer> edge : edges.cellSet()) {
            if ((Math.abs(vertices.get(edge.getRowKey()).getWeight() - vertices.get(edge.getColumnKey()).getWeight()) - edge.getValue()) < 0) {
                incorrectEntriesCounter++;
            }
        }
//        LOGGER.info("incorrect "+incorrectEntriesCounter);
        return incorrectEntriesCounter;
    }

    public int longestEdge() {
        int longest = 0;
        for (Table.Cell<Integer, Integer, Integer> cell : edges.cellSet()) {
            if (cell.getValue() > longest)
                longest = cell.getValue();
        }
        return longest;
    }

    public boolean isValid() {
        return incorrectEdges() < 1;
    }

    public int findMaxColor() {
        int maxColor = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Vertex> vertex : vertices.entrySet()) {
            int currentColor = vertex.getValue().getWeight();
            if (currentColor > maxColor) {
                maxColor = currentColor;
            }
        }
        return maxColor;
    }

    public void replaceVertex(int vertexNo, Individual other) {
        vertices.put(vertexNo, other.vertices.get(vertexNo));
    }

    public boolean hasBlankVertices() {
        for (Map.Entry<Integer, Vertex> element : vertices.entrySet()) {
            if (element.getValue().hasBlankColor())
                return true;
        }
        return false;

    }



//    public void replaceVerticesAfter(Individual other, int vertexNo) {
//        for (int i = vertexNo; i < other.getVertices().size(); i++) {
//            vertices.put(i, other.vertices.get(i));
//        }

//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        int counter =0;
//        for (Map.Entry<Integer, Vertex> entry : vertices.entrySet()) {
//            builder.append("sasiad "+counter+": " + entry.getKey() + entry.getValue());
//         //   LOGGER.info("Vertices map content: " + entry.getKey() + entry.getValue());
//            counter++;
//        }
//        return builder.toString();
}

