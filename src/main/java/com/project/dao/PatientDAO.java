package com.project.dao;

import com.project.entity.CPF;
import com.project.model.Patient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientDAO {
    public static void initi() {
        DbConnect.openBank();
        MedicalAppointmentDAO.initi();
        FutureMedicalAppointmentDAO.initi();
        String query = "CREATE TABLE IF NOT EXISTS "
                + "Patient(cpf_Patient CHAR(11) PRIMARY KEY,"
                + "nome_Patient text"
                + ");";

        try {
            DbConnect.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error PatientDAO: " + e.getMessage());
        }
    }

    public static void add(Patient patient) {
        String query = "INSERT INTO Patient "
                + "(cpf_Patient, nome_Patient)"
                + "VALUES (?, ?)";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setString(1, patient.geCpftId().getNumberCPF());
            pstmt.setString(2, patient.getName());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error PatientDAO: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    public static void delete(CPF cpf) {
        String query = "DELETE FROM Patient WHERE cpf_Patient = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, cpf.getNumberCPF());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error PatientDAO: " + e.getMessage());
        }

        DbConnect.closeBank();
    }

    public static void update(Patient patient) {
        String query = "UPDATE Patient SET "
                + "nome_Patient = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setString(1, patient.getName());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error PatientDAO: " + e.getMessage());
        }
    }

    public static Patient search(CPF cpf) {
        Patient patient = null;
        String query = "SELECT * FROM Patient WHERE cpf_Patient = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, cpf.getNumberCPF());
            ResultSet rSet = pstmt.executeQuery();

            if (rSet.next()) {
                CPF cpfPatient = new CPF(rSet.getString(1));
                String name = rSet.getString(2);

                patient = new Patient(cpfPatient, name, MedicalAppointmentDAO.search(cpf));
            }
        } catch (Exception e) {
            System.err.println("Error PatientDAO.seek: " + e.getMessage());
        }

        DbConnect.closeBank();
        return patient;
    }

    public static boolean existPatient(CPF cpf) {
        Patient patient = PatientDAO.search(cpf);
        if (patient == null)
            return false;
        return true;
    }

    public static int numberPatient() {
        String query = "SELECT COUNT(*) FROM Patient;";

        int count = 0;

        try {
            Statement stmt = DbConnect.getConnection().createStatement();
            ResultSet rSet = stmt.executeQuery(query);

            if (rSet.next())
                count = rSet.getInt("COUNT(*)");
        } catch (Exception e) {
            System.err.println("Error PatientDAO.numberPatient: " + e.getMessage());
        }
        DbConnect.closeBank();
        return count;
    }
}
