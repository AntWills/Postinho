package com.project.dao;

import java.util.List;
import java.util.ArrayList;

import com.project.entity.CPF;
import com.project.entity.Date;
import com.project.model.MedicalConsultation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FutureMedicalAppointmentDAO {
    public static void initi() {
        DbConnect.openBank();
        String query = "CREATE TABLE IF NOT EXISTS "
                + "FutureMedicalAppointment ("
                + "id_FutureMedicalAppointment INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type_FutureMedicalAppointment INTEGER NOT NULL, "
                + "cpf_patient_FutureMedicalAppointment CHAR(11) NOT NULL, "
                + "date_care_FutureMedicalAppointment CHAR(10), "
                + "reason_service_FutureMedicalAppointment TEXT"
                + ");";
        try {
            DbConnect.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error FutureMedicalCareDAO: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    public static void add(MedicalConsultation mc) {
        String query = "INSERT INTO FutureMedicalAppointment"
                + "(type_FutureMedicalAppointment, cpf_patient_FutureMedicalAppointment,"
                + "date_care_FutureMedicalAppointment, reason_service_FutureMedicalAppointment)"
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, mc.getTypeService());
            pstmt.setString(2, mc.getCpfPatient().getNumberCPF());
            pstmt.setString(3, mc.getDateService().toString());
            pstmt.setString(4, mc.getReasonForService());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalAppointmentDAO.add: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.add: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    public static void delete(int id) {
        String query = "DELETE FROM FutureMedicalAppointment Where id_FutureMedicalAppointment = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalAppointmentDAO.delete(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.delete(int): " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    public static void updade(int id, MedicalConsultation mAppointment) {
        String query = "UPDATE FutureMedicalAppointment SET "
                + "type_FutureMedicalAppointment = ?, "
                + "cpf_patient_FutureMedicalAppointment = ?, "
                + "date_care_FutureMedicalAppointment = ?, "
                + "reason_service_FutureMedicalAppointment = ? "
                + "WHERE id_FutureMedicalAppointment = " + id;
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, mAppointment.getTypeService());
            pstmt.setString(2, mAppointment.getCpfPatient().getNumberCPF());
            pstmt.setString(3, mAppointment.getDateService().toString());
            pstmt.setString(4, mAppointment.getReasonForService());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalAppointmentDAO.update: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.update: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    public static MedicalConsultation search(int id) {
        MedicalConsultation mAppointment = null;

        String query = "SELECT * FROM FutureMedicalAppointment "
                + "WHERE id_FutureMedicalAppointment = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, id);

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpfMedicalCare = new CPF(rSet.getString(3));
                Date dateMedicalCare = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                mAppointment = new MedicalConsultation(
                        idMedicalCare, type,
                        cpfMedicalCare, dateMedicalCare, reazon);

            }
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " + e.getMessage());
        }
        DbConnect.closeBank();
        return mAppointment;
    }

    public static List<MedicalConsultation> search(CPF cpf) {
        List<MedicalConsultation> list = new ArrayList<>();

        String query = "SELECT * FROM FutureMedicalAppointment "
                + "WHERE cpf_patient_FutureMedicalAppointment = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setString(1, cpf.getNumberCPF());

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpfMedicalCare = new CPF(rSet.getString(3));
                Date dateMedicalCare = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                MedicalConsultation mc = new MedicalConsultation(
                        idMedicalCare, type,
                        cpfMedicalCare, dateMedicalCare, reazon);
                list.add(mc);
            }
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(CPF): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(CPF): " + e.getMessage());
        }
        return list;
    }

    public static MedicalConsultation search(int id, CPF cpf) {
        MedicalConsultation mAppointment = null;

        String query = "SELECT * FROM FutureMedicalAppointment "
                + "WHERE id_FutureMedicalAppointment = ? AND " +
                "cpf_patient_FutureMedicalAppointment ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, id);
            pstmt.setString(2, cpf.getNumberCPF());

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpfMedicalCare = new CPF(rSet.getString(3));
                Date dateMedicalCare = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                mAppointment = new MedicalConsultation(
                        idMedicalCare, type,
                        cpfMedicalCare, dateMedicalCare, reazon);

            }
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " + e.getMessage());
        }
        DbConnect.closeBank();
        return mAppointment;
    }

    public static List<MedicalConsultation> search(Date date) {
        List<MedicalConsultation> list = new ArrayList<>();

        String query = "SELECT * FROM FutureMedicalAppointment "
                + "WHERE date_care_FutureMedicalAppointment = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setString(1, date.toString());

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpfMedicalCare = new CPF(rSet.getString(3));
                Date dateMedicalCare = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                MedicalConsultation mc = new MedicalConsultation(
                        idMedicalCare, type,
                        cpfMedicalCare, dateMedicalCare, reazon);
                list.add(mc);
            }
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(Date): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(Date): " + e.getMessage());
        }
        DbConnect.closeBank();
        return list;
    }
}
