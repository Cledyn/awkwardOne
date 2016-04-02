package model;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

public class Population {

    private static final int targetPopSize = 100;

    public List<Individual> getMembers() {
        return members;
    }

    private List<Individual> members;
    private static final Random randGenerator = new Random();

    public Population(List<Individual> members) {

        this.members = Lists.newArrayList(members);
    }

    public Population() {

        this.members = Lists.newArrayList();
    }

    public int getSize() {
        return members.size();
    }


    public void addIndividual(Individual individual) {

        members.add(individual);
    }

    public void expandPopulation() {

    }

    public boolean hasValidMember() {
        for (Individual individual : members) {
            if (individual.isValid())
                return true;
        }
        return false;
    }

    public Individual getBestMember() {
        double maxFitness = Integer.MIN_VALUE;
        int individualPosition = -1;
        for (int i = 0; i < members.size(); i++) {
            double currentFitness = members.get(i).getFitness();
            if (maxFitness < currentFitness) {
                maxFitness = currentFitness;
                individualPosition = i;
            }
        }
        return members.get(individualPosition);
    }

    public Individual getWorstMember() {
        double minFitness = Integer.MAX_VALUE;
        int individualPosition = -1;
        for (int i = 0; i < members.size(); i++) {
            double currentFitness = members.get(i).getFitness();
            if (minFitness > currentFitness) {
                minFitness = currentFitness;
                individualPosition = i;
            }
        }
        return members.get(individualPosition);

    }

    public Individual[] getNeededMembers() {
        Individual[] mem = new Individual[3];
        double minFitness = Integer.MAX_VALUE;
        double maxFitness = Integer.MIN_VALUE;
        int worstIndPosition = -1;
        int bestIndPosition = -1;
        int totalColors = 0;
        for (int i = 0; i < members.size(); i++) {
            double currentFitness = members.get(i).getFitness();
            if (minFitness > currentFitness) {
                minFitness = currentFitness;
                worstIndPosition = i;
            }
            if (maxFitness < currentFitness) {
                maxFitness = currentFitness;
                bestIndPosition = i;
            }
        }

        mem[0] = members.get(bestIndPosition);
        mem[1] = members.get(worstIndPosition);
        return mem;
    }

    public int individualSize() {
        return getMember(0).getVertices().size();
    }

    public Individual getMember(int position) {
        return members.get(position);
    }


    public void addMembers(Population parents) {
        for (Individual individual : parents.getMembers()) {
            if (this.getSize() < parents.getSize()) {
                members.add(individual);
            }
        }
    }
}
