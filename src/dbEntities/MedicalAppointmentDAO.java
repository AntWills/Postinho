package dbEntities;

import entities.patient.CPF;
import entities.patient.MedicalAppointment.*;

import java.util.List;
import java.sql.Connection;
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
    }

    public static void add(MedicalAppointment mAppointment) {
        String query = "INSERT INTO MedicalAppointment"
                + "(type_MedicalAppointment, cpf_patient_MedicalAppointment,"
                + " date_care_MedicalAppointment,reason_service_MedicalAppointment)"
                + "VALUES(?, ?, ?, ?)";
        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);

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
    }

    public static void delete(int id) {
        String query = "DELETE FROM MedicalAppointment WHERE id_MedicalAppointment = ?";
        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error MedicalAppointmentDAO.delete(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO.delete(int): " + e.getMessage());
        }
    }

    public static MedicalAppointment seek(int id) {
        MedicalAppointment mAppointments = null;

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE id_MedicalAppointment = ?";

        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);
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

        return mAppointments;
    }

    public static List<MedicalAppointment> seek(CPF cpf) {
        List<MedicalAppointment> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE cpf_patient_MedicalAppointment = ?";

        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);
            pstmt.setString(1, cpf.getNumberCPF());

            ResultSet rSet = pstmt.executeQuery();
            // System.out.println(pstmt.toString());

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

        return list;
    }

    public static List<MedicalAppointment> seek(Date date) {
        List<MedicalAppointment> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE date_care_MedicalAppointment = ?";
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
            System.err.println("Error MedicalAppointment.seek(Date): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.seek(Date): " + e.getMessage());
        }
        return list;
    }
}
