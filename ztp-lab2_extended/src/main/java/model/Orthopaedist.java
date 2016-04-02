package model;

import utils.Diagnose;
import utils.Symptom;

import java.util.List;

/**
 * Created by Sandra on 2016-03-20.
 */
public class Orthopaedist extends  Doctor{


    public Orthopaedist(String name, String surname, float salary) {
        super(name, surname, salary);
    }
    @Override
    public String advice(){
        return "Skolioza - skrzywienie kręgosłupa.";
    }

    public String diagnose(Patient patient)
    {
        Diagnose diagnose;
        String toReturn;
        List<Symptom> symptoms = patient.getSymptoms();

        if(symptoms.contains(Symptom.SPINE_PAIN) && symptoms.contains(Symptom.DIFFICULTY_IN_MOVING)){
            diagnose = Diagnose.SCIATICA;
        }
       else if(symptoms.contains(Symptom.SPINE_PAIN) || symptoms.contains(Symptom.DIFFICULTY_IN_MOVING)){
            diagnose = Diagnose.EXAMINE;
        }
        else{
            return  String.format("Brak rozpoznania. %s", super.advice(patient));
        }
        patient.setDiagnose(diagnose);
        toReturn = diagnose.toString();
        return toReturn;
    }


}
