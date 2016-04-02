package execution;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import model.Appointment;
import model.Doctor;
import model.Patient;
import utils.DoctorHolder;
import utils.Symptom;

import java.util.Date;
import java.util.List;


/**
 * Created by Sandra on 2016-03-19.
 */

public class Hospital {

    public List<Patient> patients;

    public List<? super Doctor> doctors;

    public List<Appointment<Patient, Doctor>> appointments;

    public Hospital(List<? super Doctor> doctors, List<Patient> patients, List<Appointment<Patient, Doctor>> appointments) {
        this.doctors = doctors;
        this.patients = patients;
        this.appointments = appointments;
    }

    public Hospital() {
        this.doctors = Lists.newArrayList();
        this.patients = Lists.newArrayList();
        this.appointments = Lists.newArrayList();
    }

    public void makeAppointment(Patient p, Doctor d) {

        appointments.add(new Appointment<Patient, Doctor>(p, d, new Date()));
    }

    public void showAppointments() {
        for (Appointment<Patient, Doctor> app : appointments) {
            System.out.println(app.appointmentDetails());
        }
    }

    public String conductTreatment(Patient p) {
        Doctor firstContact = new Doctor("Zenek", "Kubiak", 5000);
        firstContact.advice();
        makeAppointment(p, firstContact);
        String advice = firstContact.advice(p);
        showAppointments();
        Doctor specialist = directToSpecialist(advice);
        makeAppointment(p, specialist);
        return specialist.diagnose(p);
    }

    public Doctor directToSpecialist(String advice) {
        Doctor doctor;
        if (advice.contains("ortopedy")) {
            doctor = DoctorHolder.getSpecialist("ORTHOPAEDIST");
        } else if (advice.contains("laryngologa")) {
            doctor = DoctorHolder.getSpecialist("LARYNGOLOGIST");
        } else if (advice.contains("chirurga")) {
            doctor = DoctorHolder.getSpecialist("SURGEON");
        } else {
            doctor = DoctorHolder.getSpecialist("PEDIATRITIAN");
        }
        doctors.add(doctor);
        System.out.println(String.format("Skierowano do: %s", doctor.getClass()));
        return doctor;
    }

    public void showStaff() {
        for (Object doctor : doctors) {
            System.out.println(doctor.getClass());
        }
    }


    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        List<Symptom> sampleSymptoms = ImmutableList.of(Symptom.SPINE_PAIN, Symptom.DIFFICULTY_IN_MOVING);
        Patient samplePatient = new Patient("Ania", "Stefanowska", sampleSymptoms);
        hospital.conductTreatment(samplePatient);
        hospital.showStaff();
        hospital.showAppointments();
    }
}
