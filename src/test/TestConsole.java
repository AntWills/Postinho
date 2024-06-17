package test;

import dbEntities.PatientDAO;
import entities.patient.MedicalAppointment.*;;

public class TestConsole {
    public static void main(String[] args) {
        System.out.println("number of patient: " + PatientDAO.numberPatient());
    }
}
