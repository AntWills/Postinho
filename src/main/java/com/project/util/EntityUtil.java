package com.project.util;

import com.project.model.Patient;

public class EntityUtil {
    public static String toString(Patient patient) {
        String patientString = "";
        patientString += "Cpf: " + patient.geCpft().getStringCpf() + "\n";
        patientString += "Nome: " + patient.getName() + ".\n";
        return patientString;
    }
}
