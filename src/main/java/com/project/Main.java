package com.project;

import com.project.service.ConsultationService;
import com.project.service.DoctorService;
import com.project.service.PatientService;
import com.project.view.InitialMenu;

public class Main {

    public static void main(String[] args) {
        try {
            PatientService.start();
            DoctorService.start();
            ConsultationService.start();
        } catch (Exception e) {
            System.err.println("Ouvi um erro: " + e.getMessage());
            return;
        }
        InitialMenu.runInitialMenu();
    }
}
