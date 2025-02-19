package com.project.util;

import com.project.model.Consultation;
import com.project.model.Doctor;
import com.project.model.Patient;

public class EntityUtil {
    public static void printInTerminal(Patient patient) {
        System.out.println("Cpf: " + patient.geCpf() + ".");
        System.out.println("Nome: " + patient.getName() + ".");
    }

    public static void printInTerminal(Doctor doctor) {
        System.out.println("Código: " + doctor.getId() + ".");
        System.out.println("Nome: " + doctor.getName() + ".");
    }

    public static void printInTerminal(Consultation consultation) {
        String[] statuStrings = {
                "Agendado",
                "Realizado",
                "Cancelado",
                "Não realizado"
        };
        String[] colorStatus = {
                ColorOut.blue,
                ColorOut.green,
                ColorOut.yellow,
                ColorOut.red
        };

        System.out.println("Código: " + consultation.getId() + ".");
        System.out.println("Status: "
                + colorStatus[consultation.getStatus()]
                + statuStrings[consultation.getStatus()]
                + ColorOut.reset);
        System.out.println("Cpf do paciente: " + consultation.getPatientId() + ".");
        System.out.println("Código do doutor: " + consultation.getDoctorId() + ".");
        System.out.println("Data: " + consultation.getDate() + ".");
        System.out.println("razão: " + consultation.getReasonForService() + ".");
    }
}
