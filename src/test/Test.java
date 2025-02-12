package test;

import java.com.project.entity.MenuOptions.*;
import java.model.FutureMedicalAppointmentDAO;
import java.model.MedicalAppointmentDAO;
import java.model.PatientDAO;

public class Test {
    public static void main(String[] args) {
        MedicalAppointmentDAO.initi();
        FutureMedicalAppointmentDAO.initi();
        PatientDAO.initi();

        InitialMenu.runInitialMenu();

    }
}
