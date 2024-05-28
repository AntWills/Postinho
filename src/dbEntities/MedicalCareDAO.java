package dbEntities;

import entities.patient.CPF;
import entities.patient.MedicalCare.*;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MedicalCareDAO {
    public static void initi() {
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
                + "(type_MedicalCare,cpf_patient_MedicalCare,date_care_MedicalCare,reason_service)"
                + "VALUES('" + mc.getTypeService() + "',"
                + "'" + mc.getCpfPatient().getNumberCPF() + "',"
                + "'" + mc.getDateService().toString() + "',"
                + "'" + mc.getReasonForService() + "'"
                + ")";
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }
    }

    public static void delete(int id) {
        String query = "DELETE FROM MedicalCare WHERE id_MedicalCare = " + id;
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }
    }

    public static MedicalCare seek(int id) {
        MedicalCare mCare = null;

        String query = "SELECT * FROM MedicalCare "
                + "WHERE id_MedicalCare = '" + id + "'";

        try {
            Connection db = UtilDB.getConnection();
            Statement stm = db.createStatement();
            ResultSet rSet = stm.executeQuery(query);

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                CPF cpf = new CPF(rSet.getString(3));
                Date date = new Date(rSet.getString(4));
                String reazon = rSet.getString(5);

                mCare = new MedicalCare(idMedicalCare,
                        type, cpf, date, reazon);
            }
        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }

        return mCare;
    }

    public static List<MedicalCare> seek(CPF cpf) {
        List<MedicalCare> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalCare "
                + "WHERE cpf_patient_MedicalCare = '"
                + cpf.getNumberCPF() + "'";

        try {
            Connection db = UtilDB.getConnection();
            Statement stm = db.createStatement();
            ResultSet rSet = stm.executeQuery(query);

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

        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }

        return list;
    }
}
