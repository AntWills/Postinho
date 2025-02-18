package com.project.dao;

import com.project.exception.InvalidCpfException;
import com.project.model.Patient;
import com.project.service.ConsultationService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientDAO extends AbstractDAO<Patient, String> {
    // public static PatientDAO staticPatient = new PatientDAO();

    // @Override;
    @Override
    public void start() throws SQLException {
        DbConnect.openBank();
        String query = "";
        query += "CREATE TABLE IF NOT EXISTS " + this.getTableName() + " (";
        query += "cpf_id TEXT PRIMARY KEY,";
        query += "name TEXT";
        query += ");";

        DbConnect.execQuery(query);

    }

    // public List<Consultation> findAllConsultation(String cpf) {
    // List<Consultation> consultations = new ArrayList<>();

    // String query = "";
    // query += "SELECT";
    // query += "consultation.consultation_id,";
    // query += "consultation.patient_id,";
    // query += "consultation.doctor_id,";
    // query += "consultation.date_consultation";
    // query += "FROM " + this.getTableName() + " ";
    // query += "LEFT JOIN consultation ON patient.cpf_id =
    // consultation.patient_id";
    // query += "WHERE";
    // query += "consultation.patient_id = ?;";

    // return consultations;
    // }

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

    @Override
    public Patient mapResultSetToEntity(ResultSet rs) throws SQLException {
        String cpf = rs.getString("cpf_id");
        String name = rs.getString("name");

        Patient patient = null;
        try {
            patient = new Patient(cpf, name);
        } catch (InvalidCpfException e) {
            throw new SQLException("CPF invalido.");
        }

        patient.setConsultations(
                ConsultationService.findByPatient(patient));
        return patient;
    }

    @Override
    public String getTableName() {
        return "patient";
    }

    @Override
    protected String getSaveQuery() {
        String query = "";
        query += "INSERT INTO " + this.getTableName() + " ";
        query += "(cpf_id, name)";
        query += "VALUES (?, ?);";

        return query;
    }

    @Override
    protected void setPstmtForSave(PreparedStatement pstmt, Patient obj) throws SQLException {
        pstmt.setString(1, obj.geCpf());
        pstmt.setString(2, obj.getName());
    }

    @Override
    protected String getUpdateQuery() {
        String query = "";
        query += "UPDATE " + this.getTableName() + " SET ";
        query += "name = ?";
        query += "WHERE cpf_id = ?;";

        return query;
    }

    @Override
    protected void setPstmtForUdate(PreparedStatement pstmt, Patient obj) throws SQLException {
        pstmt.setString(1, obj.getName());
        pstmt.setString(2, obj.geCpf());
    }

}
