package Main;

import dbEntities.FutureMedicalAppointmentDAO;
import dbEntities.MedicalAppointmentDAO;
import dbEntities.PatientDAO;
import entities.MenuOptions.InitialMenu;

public class Main {
    public static void main(String[] args) {
        PatientDAO.initi();
        MedicalAppointmentDAO.initi();
        FutureMedicalAppointmentDAO.initi();

        InitialMenu.runInitialMenu();
    }
}
