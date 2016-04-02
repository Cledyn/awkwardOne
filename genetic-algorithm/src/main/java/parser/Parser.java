package parser;

import com.google.common.base.Charsets;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.io.Files;
import model.Individual;
import model.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sandra on 2016-03-07.
 */
public class Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    private static List<String> parseFile(String fileName) throws IOException {
        List<String> readLines = Files.readLines(new File(String.format("C:\\Users\\Sandra\\Desktop\\Repos\\genetic-algorithm\\src\\main\\java\\parser\\%s", fileName)), Charsets.UTF_8);
        return readLines;
    }

    public static Individual getEdges(String filename) throws IOException {
        int graphSize = Integer.parseInt(filename.substring(4,5));
        int verticesNo = getVerticesNo(filename);
        Individual individual = new Individual(verticesNo);
        Table<Vertex, Vertex, Integer> edges = HashBasedTable.create();
        List<String> lines = parseFile(filename);

        for (String line : lines) {
            if (line.startsWith("e")) {
                String[] tab = line.split("\\s+");
               int result =  individual.addEdge(Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]));
              //  LOGGER.info("Result: "+result);
//               if(result!=-1)
//                   // LOGGER.info("Adverse: "+ individual.getEdge(individual.fetchVertex(Integer.parseInt(tab[2])), individual.fetchVertex(Integer.parseInt(tab[1]))));
            }
        }

     //   individual.showEdges();
        return individual;

    }

    public static int getVerticesNo(String fileName){
        String noEnding = fileName.substring(0,fileName.length()-4);
        return Integer.parseInt(noEnding.substring(4, noEnding.length()));
    }

//
//    public static void main(String[] args) throws IOException {
//        //   Parser p = new Parser();
//        Parser.getEdges("GEOM20.col");
//    }
}
