package model;

import utils.Diagnose;
import utils.Symptom;

import java.util.List;

/**
 * Created by Sandra on 2016-03-20.
 */
public class Surgeon extends  Doctor{

    private int nightShiftBonus;

    public Surgeon(String name, String surname, float salary,int nightShiftBonus) {
        super(name, surname, salary);
        this.nightShiftBonus = nightShiftBonus;
    }

    @Override
    public String advice(){
        return Diagnose.APPENDICITIS.name();
    }

    public String diagnose(Patient patient)
    {
        String diagnose;
        List<Symptom> symptoms = patient.getSymptoms();

        if(symptoms.contains(Symptom.ABDOMINAL_ACHE) && symptoms.contains(Symptom.FEVER)){
            diagnose = Diagnose.APPENDICITIS.name();
        }
        if(symptoms.contains(Symptom.ABDOMINAL_ACHE) || symptoms.contains(Symptom.FEVER)){
            diagnose = Diagnose.EXAMINE.toString();
        }
        else{
            diagnose = String.format("Nie wiem. %s", super.advice(patient));
        }
        return diagnose;
    }

    //todo: tu koercja: (int --> float)--> float -->double
    @Override
    public double paySalary(){
        return salary+nightShiftBonus;
    }

    @Override
    public String toString(){
        return String.format("Imie : %s, nazwisko: %s, Specjalizacja: %s, wypłata: %f ( w tym %d bonusu)",name, surname, this.getClass(), salary, nightShiftBonus);
    }

}
