package com.project.dao;

import java.util.List;
import com.project.entity.patient.CPF;
import com.project.entity.patient.MedicalAppointment.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicalAppointmentDAO {
    public static void initi() {
        UtilDB.openBank();
        String query = "CREATE TABLE IF NOT EXISTS "
                + "MedicalAppointment ("
                + "id_MedicalAppointment INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type_MedicalAppointment INTEGER NOT NULL, "
                + "cpf_patient_MedicalAppointment CHAR(11) NOT NULL, "
                + "date_care_MedicalAppointment CHAR(10), "
                + "reason_service_MedicalAppointment TEXT)";
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO: " + e.getMessage());
        }
        UtilDB.closeBank();
    }

    public static void add(MedicalAppointment mAppointment) {
        String query = "INSERT INTO MedicalAppointment"
                + "(type_MedicalAppointment, cpf_patient_MedicalAppointment,"
                + " date_care_MedicalAppointment,reason_service_MedicalAppointment)"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = UtilDB.getConnection().prepareStatement(query);

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
        UtilDB.closeBank();
    }

    public static void delete(int id) {
        String query = "DELETE FROM MedicalAppointment WHERE id_MedicalAppointment = ?";
        try {
            PreparedStatement pstmt = UtilDB.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error MedicalAppointmentDAO.delete(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO.delete(int): " + e.getMessage());
        }
        UtilDB.closeBank();
    }

    public static void updade(int id, MedicalAppointment mAppointment) {
        String query = "UPDATE MedicalAppointment SET "
                + "type_MedicalAppointment = ?, "
                + "cpf_patient_MedicalAppointment = ?, "
                + "date_care_MedicalAppointment = ?, "
                + "reason_service_MedicalAppointment = ? "
                + "WHERE id_MedicalAppointment = " + id;
        try {
            PreparedStatement pstmt = UtilDB.getConnection().prepareStatement(query);

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
        UtilDB.closeBank();
    }

    public static MedicalAppointment search(int id) {
        MedicalAppointment mAppointments = null;

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE id_MedicalAppointment = ?";

        try {
            PreparedStatement pstmt = UtilDB.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpf = new CPF(rSet.getString(3));
                Date date = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                mAppointments = new MedicalAppointment(idMedicalCare,
                        type, cpf, date, reazon);
            }
        } catch (SQLException e) {
            System.err.println("Error MedicalAppointmentDAO.seek(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO.seek(int): " + e.getMessage());
        }

        UtilDB.closeBank();
        return mAppointments;
    }

    public static List<MedicalAppointment> search(CPF cpf) {
        List<MedicalAppointment> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE cpf_patient_MedicalAppointment = ?";

        try {
            PreparedStatement pstmt = UtilDB.getConnection().prepareStatement(query);
            pstmt.setString(1, cpf.getNumberCPF());

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpfMedicalCare = new CPF(rSet.getString(3));
                Date date = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                MedicalAppointment mc = new MedicalAppointment(idMedicalCare,
                        type, cpfMedicalCare, date, reazon);

                list.add(mc);
            }

        } catch (SQLException e) {
            System.err.println("Error MedicalAppointment.seek(CPF): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.seek(CPF): " + e.getMessage());
        }

        UtilDB.closeBank();
        return list;
    }

    public static MedicalAppointment search(int id, CPF cpf) {
        MedicalAppointment mAppointment = null;

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE id_MedicalAppointment = ? AND " +
                "cpf_patient_MedicalAppointment ?";
        try {
            PreparedStatement pstmt = UtilDB.getConnection().prepareStatement(query);

            pstmt.setInt(1, id);
            pstmt.setString(2, cpf.getNumberCPF());

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpfMedicalCare = new CPF(rSet.getString(3));
                Date dateMedicalCare = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                mAppointment = new MedicalAppointment(
                        idMedicalCare, type,
                        cpfMedicalCare, dateMedicalCare, reazon);

            }
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(int): " + e.getMessage());
        }

        UtilDB.closeBank();
        return mAppointment;
    }

    public static List<MedicalAppointment> search(Date date) {
        List<MedicalAppointment> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE date_care_MedicalAppointment = ?";
        try {
            PreparedStatement pstmt = UtilDB.getConnection().prepareStatement(query);

            pstmt.setString(1, date.toString());

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpfMedicalCare = new CPF(rSet.getString(3));
                Date dateMedicalCare = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                MedicalAppointment mc = new MedicalAppointment(
                        idMedicalCare, type,
                        cpfMedicalCare, dateMedicalCare, reazon);
                list.add(mc);
            }
        } catch (SQLException e) {
            System.err.println("Error MedicalAppointment.seek(Date): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.seek(Date): " + e.getMessage());
        }

        UtilDB.closeBank();
        return list;
    }
}
