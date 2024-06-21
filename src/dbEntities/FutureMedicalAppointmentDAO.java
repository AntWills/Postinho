package dbEntities;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.patient.MedicalAppointment.*;
import entities.patient.CPF;

public class FutureMedicalAppointmentDAO {
    public static void initi() {
        String query = "CREATE TABLE IF NOT EXISTS "
                + "FutureMedicalAppointment ("
                + "id_FutureMedicalAppointment INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type_FutureMedicalAppointment INTEGER NOT NULL, "
                + "cpf_patient_FutureMedicalAppointment CHAR(11) NOT NULL, "
                + "date_care_FutureMedicalAppointment CHAR(10), "
                + "reason_service_FutureMedicalAppointment TEXT"
                + ");";
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error FutureMedicalCareDAO: " + e.getMessage());
        }
    }

    public static void add(MedicalAppointment mc) {
        String query = "INSERT INTO FutureMedicalAppointment"
                + "(type_FutureMedicalAppointment, cpf_patient_FutureMedicalAppointment,"
                + "date_care_FutureMedicalAppointment, reason_service_FutureMedicalAppointment)"
                + "VALUES (?, ?, ?, ?)";

        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);

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
    }

    public static void delete(int id) {
        String query = "DELETE FROM FutureMedicalAppointment Where id_FutureMedicalAppointment = ?";

        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalAppointmentDAO.delete(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.delete(int): " + e.getMessage());
        }
    }

    public static void updade(int id, MedicalAppointment mAppointment) {
        String query = "UPDATE FutureMedicalAppointment SET "
                + "type_FutureMedicalAppointment = ?, "
                + "cpf_patient_FutureMedicalAppointment = ?, "
                + "date_care_FutureMedicalAppointment = ?, "
                + "reason_service_FutureMedicalAppointment = ? "
                + "WHERE id_FutureMedicalAppointment = " + id;
        try {
            PreparedStatement pstmt = UtilDB.getConnection().prepareStatement(query);

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
    }

    public static MedicalAppointment seek(int id) {
        MedicalAppointment mAppointment = null;

        String query = "SELECT * FROM FutureMedicalAppointment "
                + "WHERE id_FutureMedicalAppointment = ?";
        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);

            pstmt.setInt(1, id);

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
        return mAppointment;
    }

    public static List<MedicalAppointment> seek(CPF cpf) {
        List<MedicalAppointment> list = new ArrayList<>();

        String query = "SELECT * FROM FutureMedicalAppointment "
                + "WHERE cpf_patient_FutureMedicalAppointment = ?";
        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);

            pstmt.setString(1, cpf.getNumberCPF());

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
            System.err.println("Error FutureMedicalAppointmentDAO.seek(CPF): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(CPF): " + e.getMessage());
        }
        return list;
    }

    public static MedicalAppointment seek(int id, CPF cpf) {
        MedicalAppointment mAppointment = null;

        String query = "SELECT * FROM FutureMedicalAppointment "
                + "WHERE id_FutureMedicalAppointment = ? AND " +
                "cpf_patient_FutureMedicalAppointment ?";
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
        return mAppointment;
    }

    public static List<MedicalAppointment> seek(Date date) {
        List<MedicalAppointment> list = new ArrayList<>();

        String query = "SELECT * FROM FutureMedicalAppointment "
                + "WHERE date_care_FutureMedicalAppointment = ?";
        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);

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
            System.err.println("Error FutureMedicalAppointmentDAO.seek(Date): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalAppointmentDAO.seek(Date): " + e.getMessage());
        }
        return list;
    }
}
