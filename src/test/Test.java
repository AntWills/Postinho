package test;

import dbEntities.FutureMedicalAppointmentDAO;
import dbEntities.MedicalAppointmentDAO;
import dbEntities.PatientDAO;
import entities.MenuOptions.*;

public class Test {
    public static void main(String[] args) {
        MedicalAppointmentDAO.initi();
        FutureMedicalAppointmentDAO.initi();
        PatientDAO.initi();

        InitialMenu.runInitialMenu();

    }
}
