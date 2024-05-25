package dbEntities;

import entities.patient.CPF;
import entities.patient.MedicalCare.*;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MedicalCareDAO {
    public MedicalCareDAO() {
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

    public void add(MedicalCare mc, CPF cpf) {
        String query = "INSERT INTO MedicalCare"
                + "(type_MedicalCare,cpf_patient_MedicalCare,date_care_MedicalCare,reason_service)"
                + "VALUES('" + mc.getTypeService() + "',"
                + "'" + cpf.getNumberCPF() + "',"
                + "'" + mc.getDateService().toString() + "',"
                + "'" + mc.getReasonForService() + "'"
                + ")";
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM MedicalCare WHERE id_MedicalCare = " + id;
        try {
            UtilDB.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }
    }

    public List<MedicalCare> seek(CPF cpf) {
        List<MedicalCare> list = new ArrayList<>();

        try {
            Connection db = UtilDB.getConnection();
            Statement stm = db.createStatement();
            String query = "SELECT * FROM MedicalCare "
                    + "WHERE cpf_patient_MedicalCare = '"
                    + cpf.getNumberCPF() + "'";
            ResultSet rSet = stm.executeQuery(query);

            while (rSet.next()) {
                int type = rSet.getInt(2);
                String dateString = rSet.getString(4);
                String reazon = rSet.getString(5);

                MedicalCare mc = new MedicalCare(type,
                        new Date(dateString), reazon);

                list.add(mc);
            }

        } catch (Exception e) {
            System.err.println("Error MedicalCareDAO: " + e.getMessage());
        }

        return list;
    }
}
