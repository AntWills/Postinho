package test;

import dbEntities.FutureMedicalAppointmentDAO;
import dbEntities.MedicalAppointmentDAO;
import dbEntities.PatientDAO;
import dbEntities.UtilDB;
import entities.MenuOptions.*;
import entities.patient.CPF;
import entities.patient.Patient;;

public class Test {
    public static void main(String[] args) {
        MedicalAppointmentDAO.initi();
        FutureMedicalAppointmentDAO.initi();
        PatientDAO.initi();

        InitialMenu.runInitialMenu();

    }
}
