package com.project.dao;

import com.project.entity.Cpf;
import com.project.model.Patient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientDAO {
    public static void initi() {
        DbConnect.openBank();
        MedicalConsultationDAO.initi();
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

            pstmt.setString(1, patient.geCpft().getStringCpf());
            pstmt.setString(2, patient.getName());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error PatientDAO: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    public static void delete(Cpf cpf) {
        String query = "DELETE FROM Patient WHERE cpf_Patient = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, cpf.getStringCpf());
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

    public static Patient search(Cpf cpf) {
        Patient patient = null;
        String query = "SELECT * FROM Patient WHERE cpf_Patient = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, cpf.getStringCpf());
            ResultSet rSet = pstmt.executeQuery();

            if (rSet.next()) {
                Cpf cpfPatient = new Cpf(rSet.getString(1));
                String name = rSet.getString(2);

                patient = new Patient(cpfPatient, name, MedicalConsultationDAO.search(cpf));
            }
        } catch (Exception e) {
            System.err.println("Error PatientDAO.seek: " + e.getMessage());
        }

        DbConnect.closeBank();
        return patient;
    }

    public static boolean existPatient(Cpf cpf) {
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
