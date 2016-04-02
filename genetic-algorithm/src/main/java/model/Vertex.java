package model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sandra on 2016-03-07.
 */
public class Vertex {


    private static final Logger LOGGER = LoggerFactory.getLogger(Vertex.class);


    private Map<Integer,Vertex> adjacency;
    private int weight=0;

    public int getNumber() {
        return number;
    }

    private int number;

    public boolean hasBlankColor(){
        return weight==0;
    }
    public Vertex(Map<Integer,Vertex> adjacency, int weight) {
        this.adjacency = Maps.newHashMap(adjacency);
        this.weight = weight;
    }

    public Vertex( int number) {
        this.number = number;
        this.adjacency = Maps.newHashMap();
    }

    public int adjacencySize(){
        return adjacency.size();
    }
    public void addToAdjacency(Vertex v){
            adjacency.put(v.number, v);
    }

    public Vertex setColor(int weight){
        this.weight= weight;
        return this;
    }
    public Map<Integer, Vertex> getAdjacency() {
        return adjacency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex other = (Vertex) o;
        return Objects.equal(this.number, other.number);//&&Objects.equal(this.weight, other.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.number);
    }
//    @Override
//    public String toString(){
//       return  MoreObjects.toStringHelper(Vertex.class).toString();
//    }

    @Override
    public String toString(){
        Set<Map.Entry<Integer, Vertex>> entrySet =adjacency.entrySet();
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Vertex> mapEntry: entrySet){
            sb.append("Neighbour= " + mapEntry.getKey() + " Value= " + mapEntry.getValue().getNumber()+" Weight="+mapEntry.getValue().getWeight() );
          //  LOGGER.info("Neighbour= "+mapEntry.getKey()+"Value= "+mapEntry.getValue().toString());
        }
        return sb.toString();
    }
    public int getWeight() {
        return weight;
    }

    public String showAdjacency(){
        Set<Map.Entry<Integer, Vertex>> entrySet =adjacency.entrySet();
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Vertex> mapEntry: entrySet){
            sb.append(mapEntry.getValue().adjacency.toString());
        }
        return sb.toString();
    }


}
