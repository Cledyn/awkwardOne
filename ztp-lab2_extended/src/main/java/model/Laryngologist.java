package model;

import utils.Diagnose;
import utils.Symptom;

import java.util.List;

/**
 * Created by Sandra on 2016-03-19.
 */
public class Laryngologist extends Doctor {

    public Laryngologist(String name, String surname, float salary) {
        super(name, surname, salary);
    }
    @Override
    public String advice(){
        return Diagnose.TONSILITIS.name();
    }

    public String diagnose(Patient patient)
    {
        Diagnose diagnose;
        List<Symptom> symptoms = patient.getSymptoms();

        if(symptoms.contains(Symptom.EAR_ACHE) && symptoms.contains(Symptom.FEVER)){
            diagnose = Diagnose.OTITIS;
        }
        if(symptoms.contains(Symptom.EAR_ACHE) || symptoms.contains(Symptom.FEVER)){
            diagnose = Diagnose.EXAMINE;
        }
        else{
            diagnose = Diagnose.UNKNOWN;
        }
        patient.setDiagnose(diagnose);
       return diagnose.toString();
    }


}
