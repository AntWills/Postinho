package com.project.dao;

import java.util.List;

import com.project.entity.CPF;
import com.project.entity.Date;
import com.project.model.MedicalConsultation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicalAppointmentDAO {
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

    public static void add(MedicalConsultation mAppointment) {
        String query = "INSERT INTO MedicalAppointment"
                + "(type_MedicalAppointment, cpf_patient_MedicalAppointment,"
                + " date_care_MedicalAppointment,reason_service_MedicalAppointment)"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, mAppointment.getTypeService());
            pstmt.setString(2, mAppointment.getCpfPatient().getNumberCPF());
            pstmt.setString(3, mAppointment.getDateService().toString());
            pstmt.setString(4, mAppointment.getReasonForService());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error MedicalAppointmentDAO.add: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO.add: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    public static void delete(int id) {
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

    public static void updade(int id, MedicalConsultation mAppointment) {
        String query = "UPDATE MedicalAppointment SET "
                + "type_MedicalAppointment = ?, "
                + "cpf_patient_MedicalAppointment = ?, "
                + "date_care_MedicalAppointment = ?, "
                + "reason_service_MedicalAppointment = ? "
                + "WHERE id_MedicalAppointment = " + id;
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, mAppointment.getTypeService());
            pstmt.setString(2, mAppointment.getCpfPatient().getNumberCPF());
            pstmt.setString(3, mAppointment.getDateService().toString());
            pstmt.setString(4, mAppointment.getReasonForService());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error MedicalAppointmentDAO.update: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO.update: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    public static MedicalConsultation search(int id) {
        MedicalConsultation mAppointments = null;

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE id_MedicalAppointment = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpf = new CPF(rSet.getString(3));
                Date date = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                mAppointments = new MedicalConsultation(idMedicalCare,
                        type, cpf, date, reazon);
            }
        } catch (SQLException e) {
            System.err.println("Error MedicalAppointmentDAO.seek(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO.seek(int): " + e.getMessage());
        }

        DbConnect.closeBank();
        return mAppointments;
    }

    public static List<MedicalConsultation> search(CPF cpf) {
        List<MedicalConsultation> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE cpf_patient_MedicalAppointment = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, cpf.getNumberCPF());

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpfMedicalCare = new CPF(rSet.getString(3));
                Date date = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                MedicalConsultation mc = new MedicalConsultation(idMedicalCare,
                        type, cpfMedicalCare, date, reazon);

                list.add(mc);
            }

        } catch (SQLException e) {
            System.err.println("Error MedicalAppointment.seek(CPF): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.seek(CPF): " + e.getMessage());
        }

        DbConnect.closeBank();
        return list;
    }

    public static MedicalConsultation search(int id, CPF cpf) {
        MedicalConsultation mAppointment = null;

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE id_MedicalAppointment = ? AND " +
                "cpf_patient_MedicalAppointment ?";
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

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE date_care_MedicalAppointment = ?";
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
            System.err.println("Error MedicalAppointment.seek(Date): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.seek(Date): " + e.getMessage());
        }

        DbConnect.closeBank();
        return list;
    }
}
