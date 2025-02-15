package com.project.dao;

import com.project.entity.Cpf;
import com.project.exception.InvalidCpfException;
import com.project.model.Patient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements Idao<Patient, Cpf> {
    // public static PatientDAO staticPatient = new PatientDAO();

    // @Override;
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

    @Override
    public Patient mapResultSetToEntity(ResultSet rs) throws InvalidCpfException, SQLException {
        Cpf cpf = new Cpf(rs.getString("cpf_Patient"));
        String name = rs.getString("nome_Patient");

        return new Patient(cpf, name);
    }

    @Override
    public void save(Patient patient) {
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

    @Override
    public void update(Patient patient) {
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

    @Override
    public void delete(Cpf cpf) {
        String query = "DELETE FROM Patient WHERE cpf_Patient = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, cpf.getStringCpf());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error.PatientDAO.delete: " + e.getMessage());
        }

        DbConnect.closeBank();
    }

    @Override
    public Patient findById(Cpf cpf) {
        Patient patient = null;
        String query = "SELECT * FROM Patient WHERE cpf_Patient = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, cpf.getStringCpf());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                patient = this.mapResultSetToEntity(rs);
            }
        } catch (Exception e) {
            System.err.println("Error PatientDAO.seek: " + e.getMessage());
        }

        DbConnect.closeBank();
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<Patient>();

        String query = "SELECT * FROM Patient";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            ResultSet rSet = pstmt.executeQuery();

            if (rSet.next()) {
                Patient patient = this.mapResultSetToEntity(rSet);
                patients.add(patient);
            }
        } catch (Exception e) {
            System.err.println("Error PatientDAO.findAll: " + e.getMessage());
        }

        DbConnect.closeBank();
        return patients;
    }

    public boolean existPatient(Cpf cpf) {
        // Patient patient = PatientDAO.findById(cpf);
        Patient patient = this.findById(cpf);
        if (patient == null)
            return false;
        return true;
    }

    public int numberPatient() {
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
