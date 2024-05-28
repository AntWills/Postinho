package test;

import dbEntities.*;
import entities.patient.CPF;
import entities.patient.MedicalCare.Date;
import entities.patient.MedicalCare.MedicalCare;

import java.util.*;

public class TestDAO {
    public static void main(String[] args) {

        FutureMedicalCareDAO.initi();

        MedicalCare mc = FutureMedicalCareDAO.seek(3);

        System.out.println(mc);
    }
}
