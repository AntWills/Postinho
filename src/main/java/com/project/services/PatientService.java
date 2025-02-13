package com.project.services;

import java.util.List;

import com.project.entity.CPF;
import com.project.model.MedicalConsultation;
import com.project.model.Patient;
// import com.project.util.ReadData;

public class PatientService {
    private static void inTerminal(Patient patient) {
        // // patient.setCpfId(CPF.inTerminal("Digite o CPF: "));
        // System.err.print("Digite o nome: ");
        // patient.setName(ReadData.STRING());
    }

    public static Patient readDataInTerminal(String msg) {
        Patient patient = new Patient();
        System.out.println(msg);
        PatientService.inTerminal(patient);
        return patient;
    }

    public static void printAllConsultations(Patient patient) {
        List<MedicalConsultation> listMedAppoint = patient.getConsultations();
        if (listMedAppoint.size() == 0) {
            // System.err.println("O paciente " + patient.getName() + " ainda não fez
            // consultas.");
            // return;
        }

        System.err.println("\nTotas as consultas feitas por " + patient.getName() +
                ":\n");

        for (MedicalConsultation care : listMedAppoint) {
            System.err.println(care + "\n");
        }

    }
}
