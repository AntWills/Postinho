package test;

import dbEntities.*;
import entities.patient.CPF;
import entities.patient.Patient;
import entities.patient.MedicalCare.*;

import java.util.List;
import java.util.ArrayList;

public class TestPatient {
    public static void main(String[] args) {
        PatientDAO.initi();

        // PatientDAO.add(new Patient(
        // new CPF("000.000.000-01"),
        // "João Silva"));

        Patient patient = PatientDAO.seek(new CPF("000.000.000-01"));

        System.out.println(patient);
    }
}
