package dbEntities;

import entities.patient.CPF;
import entities.patient.MedicalCare.*;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicalCareDAO {
    public static void initi() {
        UtilDB.openBank();
        String query = "CREATE TABLE IF NOT EXISTS "
                + "MedicalCare ("
                + "id_MedicalCare INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type_MedicalCare INTEGER NOT NULL, "
                + "cpf_patient_MedicalCare CHAR(11) NOT NULL, "
                + "date_care_MedicalCare CHAR(10), "
                + "reason_service TEXT)";
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }
    }

    public static void add(MedicalCare mc) {
        String query = "INSERT INTO MedicalCare"
                + "(type_MedicalCare, cpf_patient_MedicalCare, date_care_MedicalCare,reason_service)"
                + "VALUES(?, ?, ?, ?)";
        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);

            pstmt.setInt(1, mc.getTypeService());
            pstmt.setString(2, mc.getCpfPatient().getNumberCPF());
            pstmt.setString(3, mc.getDateService().toString());
            pstmt.setString(4, mc.getReasonForService());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error MedicalCare.add: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalCare.add: " + e.getMessage());
        }
    }

    public static void delete(int id) {
        String query = "DELETE FROM MedicalCare WHERE id_MedicalCare = ?";
        try {
            Connection db = UtilDB.getConnection();
            PreparedStatement pstmt = db.prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error MedicalCare.delete(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalCare.delete(int): " + e.getMessage());
        }
    }

    public static MedicalCare seek(int id) {
        MedicalCare mCare = null;

        String query = "SELECT * FROM MedicalCare "
                + "WHERE id_MedicalCare = ?";

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

                mCare = new MedicalCare(idMedicalCare,
                        type, cpf, date, reazon);
            }
        } catch (SQLException e) {
            System.err.println("Error MedicalCare.seek(int): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalCare.seek(int): " + e.getMessage());
        }

        return mCare;
    }

    public static List<MedicalCare> seek(CPF cpf) {
        List<MedicalCare> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalCare "
                + "WHERE cpf_patient_MedicalCare = ?";

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

                MedicalCare mc = new MedicalCare(idMedicalCare,
                        type, cpfMedicalCare, date, reazon);

                list.add(mc);
            }

        } catch (SQLException e) {
            System.err.println("Error MedicalCare.seek(CPF): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalCare.seek(CPF): " + e.getMessage());
        }

        return list;
    }

    public static List<MedicalCare> seek(Date date) {
        List<MedicalCare> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalCare "
                + "WHERE date_care_MedicalCare = ?";
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
            System.err.println("Error MedicalCare.seek(Date): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error MedicalCare.seek(Date): " + e.getMessage());
        }
        return list;
    }
}
