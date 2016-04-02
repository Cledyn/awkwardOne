package model;

import utils.Symptom;

/**
 * Created by Sandra on 2016-03-19.
 */
public  class Doctor extends Person {

    protected float salary;

    public Doctor() {
        super();
        this.salary =4000;
    }

    public Doctor(String name, String surname, float salary) {
        super(name, surname);
        this.salary = salary;
    }

    //todo: diagnose() - przesłonięte w klasach pochodnych klasie Doctor, diagnose() i diagnose(Patient patient) -
    public String advice(){
        return "Idź do specjalisty.";
    }

    public String advice(Patient patient){
        Symptom symptom = patient.getSymptoms().get(0);
        String referral = "Idź do ";
        switch(symptom){
            case SPINE_PAIN:
                referral+="ortopedy";
                break;
            case FEVER:
                referral+="lekarza rodzinnego";
                break;
            case ABDOMINAL_ACHE:
                referral+="chirurga";
                break;
            case EAR_ACHE:
                referral+="laryngologa";
                break;
            default:
                referral="Napij się ziółek, idź pobiegać, rzuć palenie.";
        }
        System.out.println(referral);
        return referral;
    }
    public String diagnose(Patient patient){
        return "Diagnoza";
    }

    //todo: w appointmentDurationInUnit() zawężenie typu, tu - rozszerzenie typu (koercja rozszerzająca)
    protected double paySalary(){
        return  salary;
    }

    @Override
    public String toString(){
        return String.format("Lekarz: [Imie : %s, nazwisko: %s, Specjalizacja:%s, wypłata: %f]",name, surname,this.getClass(), salary);
    }

}
