package com.project;

import com.project.entity.MenuOptions.InitialMenu;
import com.project.dao.*;;

// public class Main {
//     public static void main(String[] args) {
//         System.out.println("Hello world!");
//     }
// }

public class Main {
    public static void main(String[] args) {
        PatientDAO.initi();
        MedicalAppointmentDAO.initi();
        FutureMedicalAppointmentDAO.initi();

        InitialMenu.runInitialMenu();
    }
}
