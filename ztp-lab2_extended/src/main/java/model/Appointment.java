package model;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sandra on 2016-03-20.
 */

//todo: poliformizm parametryczny
public class Appointment<A, B> {
    private static final int APP_UNIT_TIME=10;
    public static final int MAX_APPOINTMENT_DURATION=284;


    public Appointment(A firstPerson, B secondPerson, Date time) {
        this.firstPerson = firstPerson;
        this.secondPerson = secondPerson;
        this.time = time;
        this.durationInMins = getApproxDuration();
    }

    protected A firstPerson;
    protected B secondPerson;
    private Date time;
    //todo: automatyczne rzutowanie
    private Integer durationInMins;



   public String appointmentDetails(){
       return String.format("Appointment took: %s (duration in units: %d , involves: %s and %s",time.toString(), appointmentDurationInUnits(),firstPerson.toString(), secondPerson.toString());
   }


    //todo: automatyczna koercja (jakby się dodało (int) to intellij mówi,że nie trzeba
    private int appointmentDurationInUnits(){
       return durationInMins%APP_UNIT_TIME!=0 ? (durationInMins/APP_UNIT_TIME) +1 : durationInMins/APP_UNIT_TIME;
    }

    public static int getApproxDuration(){
        return ThreadLocalRandom.current().nextInt(0,Appointment.MAX_APPOINTMENT_DURATION+1);
    }
}
