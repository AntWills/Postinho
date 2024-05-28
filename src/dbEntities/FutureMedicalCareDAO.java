package dbEntities;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.patient.MedicalCare.*;
import entities.patient.CPF;

public class FutureMedicalCareDAO {
    public static void initi() {
        String query = "CREATE TABLE IF NOT EXISTS "
                + "FutureMedicalCare ("
                + "id_FutureMedicalCare INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type_FutureMedicalCare INTEGER NOT NULL, "
                + "cpf_patient_FutureMedicalCare CHAR(11) NOT NULL, "
                + "date_care_FutureMedicalCare CHAR(10), "
                + "reason_service_FutureMedicalCare TEXT"
                + ");";
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error FutureMedicalCareDAO: " + e.getMessage());
        }
    }

    public static void add(MedicalCare mc) {
        String query = "INSERT INTO FutureMedicalCare"
                + "(type_FutureMedicalCare, cpf_patient_FutureMedicalCare,"
                + "date_care_FutureMedicalCare, reason_service_FutureMedicalCare)"
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
            System.err.println("Error FutureMedicalCareDAO.add: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalCareDAO.add: " + e.getMessage());
        }
    }

    public static void delete(int id) {
        String query = "DELETE FROM FutureMedicalCare Where id_FutureMedicalCare = ?";

        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalCareDAO.add: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalCareDAO.add: " + e.getMessage());
        }
    }

    public static List<MedicalCare> seek(Date date) {
        List<MedicalCare> list = new ArrayList<>();

        String query = "SELECT * FROM FutureMedicalCare "
                + "WHERE date_care_FutureMedicalCare = ?";
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

                MedicalCare mc = new MedicalCare(
                        idMedicalCare, type,
                        cpfMedicalCare, dateMedicalCare, reazon);
                list.add(mc);
            }
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalCareDAO.add: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalCareDAO.add: " + e.getMessage());
        }
        return list;
    }

    public static List<MedicalCare> seek(CPF cpf) {
        List<MedicalCare> list = new ArrayList<>();

        String query = "SELECT * FROM FutureMedicalCare "
                + "WHERE cpf_patient_FutureMedicalCare = ?";
        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);

            pstmt.setString(1, cpf.getNumberCPF());

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                System.out.println("aa\n");
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpfMedicalCare = new CPF(rSet.getString(3));
                Date dateMedicalCare = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                MedicalCare mc = new MedicalCare(
                        idMedicalCare, type,
                        cpfMedicalCare, dateMedicalCare, reazon);
                list.add(mc);
            }
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalCareDAO.add: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalCareDAO.add: " + e.getMessage());
        }
        return list;
    }

    public static MedicalCare seek(int id) {
        MedicalCare mc = null;

        String query = "SELECT * FROM FutureMedicalCare "
                + "WHERE id_FutureMedicalCare = ?";
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

                mc = new MedicalCare(
                        idMedicalCare, type,
                        cpfMedicalCare, dateMedicalCare, reazon);

            }
        } catch (SQLException e) {
            System.err.println("Error FutureMedicalCareDAO.add: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error FutureMedicalCareDAO.add: " + e.getMessage());
        }
        return mc;
    }
}
