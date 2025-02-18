package com.project;

import com.project.service.ConsultationService;
import com.project.service.DoctorService;
import com.project.service.PatientService;
import com.project.view.Menu.InitialMenu;

public class Main {

    public static void main(String[] args) {
        PatientService.start();
        DoctorService.start();
        ConsultationService.start();

        InitialMenu.runInitialMenu();
    }
}
