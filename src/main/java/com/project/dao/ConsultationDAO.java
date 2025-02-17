package com.project.dao;

import java.util.List;

import com.project.entity.Date;
import com.project.model.Consultation;
import com.project.model.Doctor;
import com.project.model.Patient;
import com.project.service.DoctorService;
import com.project.service.PatientService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultationDAO implements Idao<Consultation, Integer> {
    public void initi() {
        DbConnect.openBank();
        String query = "";
        query += "CREATE TABLE IF NOT EXISTS " + this.getTableName() + " (";
        query += "consultation_id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += "patient_id TEXT NOT NULL, ";
        query += "doctor_id INTEGER NOT NULL, ";
        query += "date_consultation TEXT, ";
        query += "reason_service TEXT";
        query += "FOREIGN KEY (patient_id) REFERENCES Patient(cpf_id), ";
        query += "FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id)";
        query += ");";

        try {
            DbConnect.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public void save(Consultation consultation) {
        // String query = "INSERT INTO MedicalAppointment"
        // + "(type_MedicalAppointment, cpf_patient_MedicalAppointment,"
        // + " date_care_MedicalAppointment,reason_service_MedicalAppointment)"
        // + "VALUES(?, ?, ?, ?)";

        String query = "";
        query += "INSERT INTO " + this.getTableName() + " ";
        query += "(patient_id, doctor_id, date_consultation) ";
        query += "VALUES(?, ?, ?)";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setString(1, consultation.getPatient().geCpft().getStringCpf());
            pstmt.setInt(2, consultation.geDoctor().getId());
            pstmt.setString(3, consultation.getDateConsultation().getData().toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error." + this.getTableName() + "DAO.save: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error." + this.getTableName() + "DAO.save: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public void update(Consultation consultation) {
        String query = "";
        query += "UPDATE " + this.getTableName() + " SET ";
        query += "patient_id = ?, ";
        query += "doctor_id = ?, ";
        query += "date_consultation = ?, ";
        query += "WHERE consultation_id = " + consultation.getID();

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setString(1, consultation.getPatient().geCpft().getStringCpf());
            pstmt.setInt(2, consultation.geDoctor().getId());
            pstmt.setString(3, consultation.getDateConsultation().getData().toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error." + this.getTableName() + "DAO.update: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error." + this.getTableName() + "DAO.update: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM " + this.getTableName() + " WHERE consultation_id = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error." + this.getTableName() + "DAO.delete: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error." + this.getTableName() + "DAO.delete: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public Consultation findById(Integer id) {
        Consultation consultation = null;

        String query = "";
        query += "SELECT * FROM " + this.getTableName() + " ";
        query += "WHERE consultation_id = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                consultation = this.mapResultSetToEntity(rs);
            }
        } catch (Exception e) {
            System.err.println("Error.MedicalAppointmentDAO.findById: " + e.getMessage());
        }

        DbConnect.closeBank();
        return consultation;
    }

    @Override
    public List<Consultation> findAll() {
        List<Consultation> list = new ArrayList<>();
        String query = "SELECT * FROM " + this.getTableName();

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Consultation mc = this.mapResultSetToEntity(rs);
                list.add(mc);
            }
        } catch (Exception e) {
            System.err.println("Error.MedicalAppointment.findAll: " + e.getMessage());
        }

        DbConnect.closeBank();
        return list;
    }

    public List<Consultation> findByPatient(Patient patient) {
        List<Consultation> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE cpf_patient_MedicalAppointment = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, patient.geCpft().getStringCpf());

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

    public Consultation findByIdAndPatient(Integer id, Patient patient) {
        Consultation consultation = null;

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE id_MedicalAppointment = ? AND " +
                "cpf_patient_MedicalAppointment ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, id);
            pstmt.setString(2, patient.geCpft().getStringCpf());

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

    public List<Consultation> findByDate(Date date) {
        List<Consultation> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE date_care_MedicalAppointment = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, date.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                this.mapResultSetToEntity(rs);
                Consultation mc = this.mapResultSetToEntity(rs);
                list.add(mc);
            }
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.findByDate: " + e.getMessage());
        }

        DbConnect.closeBank();
        return list;
    }

    @Override
    public Consultation mapResultSetToEntity(ResultSet rs) throws Exception {
        int idConsultation = rs.getInt("consultation_id");
        int type = 0;

        Cpf cpf = new Cpf(rs.getString("patient_id"));
        Patient patient = PatientService.findById(cpf);

        int idDoctor = rs.getInt("doctor_id");
        Doctor doctor = DoctorService.findById(idDoctor);

        Date dateconsultation = new Date(rs.getString("date_consultation"));
        String reazon = rs.getString("reazon");

        return new Consultation(idConsultation, type, patient, doctor, dateconsultation, reazon);
    }

    @Override
    public String getTableName() {
        return "Consultation";
    }

}
