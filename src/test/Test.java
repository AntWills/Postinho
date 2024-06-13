package test;

import dbEntities.FutureMedicalCareDAO;
import dbEntities.MedicalCareDAO;
import dbEntities.PatientDAO;
import dbEntities.UtilDB;
import entities.MenuOptions.*;
import entities.patient.Patient;;

public class Test {
    public static void main(String[] args) {
        MedicalCareDAO.initi();
        FutureMedicalCareDAO.initi();
        PatientDAO.initi();

        InitialMenu.runInitialMenu();
    }
}
