package com.project.dao;

import java.util.List;

import com.project.entity.Cpf;
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
    public static void initi() {
        DbConnect.openBank();
        String query = "CREATE TABLE IF NOT EXISTS "
                + "MedicalAppointment ("
                + "id_MedicalAppointment INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type_MedicalAppointment INTEGER NOT NULL, "
                + "cpf_patient_MedicalAppointment CHAR(11) NOT NULL, "
                + "date_care_MedicalAppointment CHAR(10), "
                + "reason_service_MedicalAppointment TEXT)";
        try {
            DbConnect.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public void save(Consultation mAppointment) {
        String query = "INSERT INTO MedicalAppointment"
                + "(type_MedicalAppointment, cpf_patient_MedicalAppointment,"
                + " date_care_MedicalAppointment,reason_service_MedicalAppointment)"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, mAppointment.getTypeService());
            pstmt.setString(2, mAppointment.getPatient().getStringCpf());
            pstmt.setString(3, mAppointment.getDateConsultation().toString());
            pstmt.setString(4, mAppointment.getReasonForService());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error MedicalAppointmentDAO.add: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO.add: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public void update(Consultation consultation) {
        String query = "UPDATE MedicalAppointment SET "
                + "type_MedicalAppointment = ?, "
                + "cpf_patient_MedicalAppointment = ?, "
                + "date_care_MedicalAppointment = ?, "
                + "reason_service_MedicalAppointment = ? "
                + "WHERE id_MedicalAppointment = " + consultation.getID();
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, consultation.getTypeService());
            pstmt.setString(2, consultation.getPatient().getStringCpf());
            pstmt.setString(3, consultation.getDateConsultation().toString());
            pstmt.setString(4, consultation.getReasonForService());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error MedicalAppointmentDAO.update: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO.update: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM MedicalAppointment WHERE id_MedicalAppointment = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error MedicalAppointmentDAO.delete(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO.delete(int): " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public Consultation findById(Integer id) {
        Consultation mAppointments = null;

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE id_MedicalAppointment = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                mAppointments = this.mapResultSetToEntity(rs);
            }
        } catch (Exception e) {
            System.err.println("Error.MedicalAppointmentDAO.findById: " + e.getMessage());
        }

        DbConnect.closeBank();
        return mAppointments;
    }

    @Override
    public List<Consultation> findAll() {
        List<Consultation> list = new ArrayList<>();
        String query = "SELECT * FROM MedicalAppointment";

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

}
