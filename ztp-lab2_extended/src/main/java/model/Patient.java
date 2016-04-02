package model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import utils.Diagnose;
import utils.Symptom;

import java.util.List;

/**
 * Created by Sandra on 2016-03-20.
 */
public class Patient extends Person {


    private List<Symptom> symptoms;



    private Diagnose diagnose;

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }


    public Patient(String name, String surname,List<Symptom>  symptoms) {
        super(name, surname);
        this.symptoms = Lists.newArrayList(symptoms);
    }

    public Patient(String name, String surname,Symptom... symptoms) {
        super(name, surname);

        this.symptoms = Lists.newArrayList(symptoms);
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    @Override
    public String toString(){
        return String.format("Pacjent: [Imie : %s, nazwisko: %s, Objawy: %s, Diagnoza: %s]",name, surname,symptoms.toString(), diagnose==null ? "brak":diagnose.toString());
    }

}
