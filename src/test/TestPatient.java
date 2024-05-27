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

        PatientDAO.delete(new CPF("00000000001"));

    }
}
