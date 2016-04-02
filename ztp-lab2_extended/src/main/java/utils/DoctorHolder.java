package utils;

import model.*;

/**
 * Created by Sandra on 2016-03-20.
 */
public class DoctorHolder {

    private static Laryngologist laryngologist;
    private static Orthopaedist orthopaedist;
    private static Pediatritian pediatritian;
    private static Surgeon surgeon;

    public static Doctor getSpecialist(String specialization) {
        if (specialization == null) {
            return null;
        }
        if (specialization.equalsIgnoreCase("LARYNGOLOGIST")) {
            if (laryngologist == null) {
                laryngologist = new Laryngologist("Jan", "Kowalski", 7000);
            }
            return laryngologist;

        } else if (specialization.equalsIgnoreCase("ORTHOPAEDIST")) {
            if (orthopaedist == null) {
                orthopaedist = new Orthopaedist("Kamil", "Nowak", 9000);
            }
            return orthopaedist;

        } else if (specialization.equalsIgnoreCase("PEDIATRITIAN")) {
            if (pediatritian == null) {
                pediatritian = new Pediatritian("Katarzyna", "Zaleska", 4000);
            }
            return pediatritian;
        } else if (specialization.equalsIgnoreCase("SURGEON")) {
            if (surgeon == null) {
                surgeon = new Surgeon("Zofia", "Stajszczyk", 11000, 600);
            }
            return surgeon;
        }

        return null;
    }
}
