package com.project.dao;

import java.util.List;

import com.project.model.Consultation;
import com.project.model.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConsultationDAO extends AbstractDAO<Consultation, Integer> {
    @Override
    public void start() throws SQLException {
        DbConnect.openBank();
        String query = "";
        query += "CREATE TABLE IF NOT EXISTS " + this.getTableName() + " (";
        query += "consultation_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += "patient_id TEXT NOT NULL, ";
        query += "doctor_id INTEGER NOT NULL, ";
        query += "status INTEGER NOT NULL, ";
        query += "date_consultation TEXT, ";
        query += "reason TEXT, ";
        query += "FOREIGN KEY (patient_id) REFERENCES Patient(cpf_id), ";
        query += "FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id)";
        query += ");";

        DbConnect.execQuery(query);

        DbConnect.closeBank();
    }

    public List<Consultation> findByPatient(Patient patient) throws SQLException {
        List<Consultation> list = new ArrayList<>();

        String query = "SELECT * FROM " + this.getTableName() + " ";
        query += "WHERE patient_id = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, patient.geCpf());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Consultation mc = this.mapResultSetToEntity(rs);
                list.add(mc);
            }

        } catch (Exception e) {
            System.err.println("Error.MedicalAppointment.findByCpf: " + e.getMessage());
        }

        DbConnect.closeBank();
        return list;
    }

    public Consultation findByIdAndPatient(Integer id, Patient patient) throws SQLException {
        Consultation consultation = null;

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE id_MedicalAppointment = ? AND " +
                "cpf_patient_MedicalAppointment ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, id);
            pstmt.setString(2, patient.geCpf());

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                consultation = this.mapResultSetToEntity(rSet);
            }
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " + e.getMessage());
        }

        DbConnect.closeBank();
        return consultation;
    }

    public List<Consultation> findByDate(LocalDate date) throws SQLException {
        List<Consultation> consultations = new ArrayList<>();

        String query = "SELECT * FROM " + this.getTableName() + " ";
        query += "WHERE date_consultation = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, date.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Consultation mc = this.mapResultSetToEntity(rs);
                consultations.add(mc);
            }
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.findByDate: " + e.getMessage());
        }

        DbConnect.closeBank();
        return consultations;
    }

    public Consultation findByPatientAndDate(Patient patient, LocalDate date) throws SQLException {
        Consultation consultation = null;

        String query = "SELECT * FROM " + this.getTableName() + " ";
        query += "WHERE patient_id = ? AND date_consultation = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, patient.geCpf());
            pstmt.setString(2, date.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                consultation = this.mapResultSetToEntity(rs);
            }
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.findByDateAfter: " + e.getMessage());
        }

        DbConnect.closeBank();
        return consultation;
    }

    public List<Consultation> findByPatientAndDateBefore(Patient patient, LocalDate date) throws SQLException {
        List<Consultation> consultations = new ArrayList<>();

        String query = "SELECT * FROM " + this.getTableName() + " ";
        query += "WHERE patient_id = ? AND date_consultation < ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, patient.geCpf());
            pstmt.setString(2, date.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Consultation consultation = this.mapResultSetToEntity(rs);
                consultations.add(consultation);
            }
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.findByDateAfter: " + e.getMessage());
        }

        DbConnect.closeBank();
        return consultations;
    }

    public List<Consultation> findByPatientAndDateAfter(Patient patient, LocalDate date) throws SQLException {
        List<Consultation> consultations = new ArrayList<>();

        String query = "SELECT * FROM " + this.getTableName() + " ";
        query += "WHERE patient_id = ? AND date_consultation > ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, patient.geCpf());
            pstmt.setString(2, date.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Consultation consultation = this.mapResultSetToEntity(rs);
                consultations.add(consultation);
            }
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.findByDateAfter: " + e.getMessage());
        }

        DbConnect.closeBank();
        return consultations;
    }

    @Override
    public Consultation mapResultSetToEntity(ResultSet rs) throws SQLException {
        int idConsultation = rs.getInt("consultation_id");

        String patientId = rs.getString("patient_id");

        int doctorId = rs.getInt("doctor_id");

        int status = rs.getInt("status");

        LocalDate date = LocalDate.parse(rs.getString("date_consultation"));
        String reazon = rs.getString("reazon");

        return new Consultation(idConsultation, status, patientId, doctorId, date, reazon);
    }

    @Override
    public String getTableName() {
        return "Consultation";
    }

    @Override
    protected String getSaveQuery() {
        String query = "";
        query += "INSERT INTO " + this.getTableName() + " ";
        query += "(patient_id, doctor_id, status, date_consultation, reazon) ";
        query += "VALUES(?, ?, ?, ?, ?)";

        return query;
    }

    @Override
    protected void setPstmtForSave(PreparedStatement pstmt, Consultation obj) throws SQLException {
        pstmt.setString(1, obj.getPatientId());
        pstmt.setInt(2, obj.getDoctorId());
        pstmt.setInt(3, obj.getStatus());
        pstmt.setString(4, obj.getDate().toString());
        pstmt.setString(5, obj.getReasonForService());
    }

    @Override
    protected String getUpdateQuery() {
        String query = "";
        query += "UPDATE " + this.getTableName() + " SET ";
        query += "patient_id = ?, ";
        query += "doctor_id = ?, ";
        query += "status = ?, ";
        query += "date_consultation = ?, ";
        query += "reazon = ?";
        query += "WHERE consultation_id = ?";

        return query;
    }

    @Override
    protected void setPstmtForUdate(PreparedStatement pstmt, Consultation obj) throws SQLException {
        pstmt.setString(1, obj.getPatientId());
        pstmt.setInt(2, obj.getDoctorId());
        pstmt.setInt(3, obj.getStatus());
        pstmt.setString(4, obj.getDate().toString());
        pstmt.setString(5, obj.getReasonForService());
        pstmt.setInt(6, obj.getId());
    }
}
