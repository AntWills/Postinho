package com.project;

import com.project.dao.*;
import com.project.view.Menu.InitialMenu;

public class Main {
    public static void main(String[] args) {
        PatientDAO.initi();
        MedicalAppointmentDAO.initi();
        FutureMedicalAppointmentDAO.initi();

        InitialMenu.runInitialMenu();
    }
}
