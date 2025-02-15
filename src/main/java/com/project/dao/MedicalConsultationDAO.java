package com.project.dao;

import java.util.List;

import com.project.entity.Cpf;
import com.project.entity.Date;
import com.project.model.MedicalConsultation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicalConsultationDAO implements Idao<MedicalConsultation, Integer> {
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
    public void save(MedicalConsultation mAppointment) {
        String query = "INSERT INTO MedicalAppointment"
                + "(type_MedicalAppointment, cpf_patient_MedicalAppointment,"
                + " date_care_MedicalAppointment,reason_service_MedicalAppointment)"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, mAppointment.getTypeService());
            pstmt.setString(2, mAppointment.getCpfPatient().getStringCpf());
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

    @Override
    public void update(MedicalConsultation mAppointment) {
        String query = "UPDATE MedicalAppointment SET "
                + "type_MedicalAppointment = ?, "
                + "cpf_patient_MedicalAppointment = ?, "
                + "date_care_MedicalAppointment = ?, "
                + "reason_service_MedicalAppointment = ? "
                + "WHERE id_MedicalAppointment = " + mAppointment.getID();
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, mAppointment.getTypeService());
            pstmt.setString(2, mAppointment.getCpfPatient().getStringCpf());
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
    public MedicalConsultation findById(Integer id) {
        MedicalConsultation mAppointments = null;

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
    public List<MedicalConsultation> findAll() {
        List<MedicalConsultation> list = new ArrayList<>();
        String query = "SELECT * FROM MedicalAppointment";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MedicalConsultation mc = this.mapResultSetToEntity(rs);
                list.add(mc);
            }
        } catch (Exception e) {
            System.err.println("Error.MedicalAppointment.findAll: " + e.getMessage());
        }

        DbConnect.closeBank();
        return list;
    }

    public List<MedicalConsultation> findByCpf(Cpf cpf) {
        List<MedicalConsultation> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE cpf_patient_MedicalAppointment = ?";

        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, cpf.getStringCpf());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MedicalConsultation mc = this.mapResultSetToEntity(rs);
                list.add(mc);
            }

        } catch (Exception e) {
            System.err.println("Error.MedicalAppointment.findByCpf: " + e.getMessage());
        }

        DbConnect.closeBank();
        return list;
    }

    public MedicalConsultation findByIdAndCpf(Integer id, Cpf cpf) {
        MedicalConsultation mAppointment = null;

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE id_MedicalAppointment = ? AND " +
                "cpf_patient_MedicalAppointment ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);

            pstmt.setInt(1, id);
            pstmt.setString(2, cpf.getStringCpf());

            ResultSet rSet = pstmt.executeQuery();

            while (rSet.next()) {
                int idMedicalCare = rSet.getInt(1);
                int type = rSet.getInt(2);
                Cpf cpfMedicalCare = new Cpf(rSet.getString(3));
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

    public List<MedicalConsultation> findByDate(Date date) {
        List<MedicalConsultation> list = new ArrayList<>();

        String query = "SELECT * FROM MedicalAppointment "
                + "WHERE date_care_MedicalAppointment = ?";
        try {
            PreparedStatement pstmt = DbConnect.getConnection().prepareStatement(query);
            pstmt.setString(1, date.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                this.mapResultSetToEntity(rs);
                MedicalConsultation mc = this.mapResultSetToEntity(rs);
                list.add(mc);
            }
        } catch (Exception e) {
            System.err.println("Error MedicalAppointment.findByDate: " + e.getMessage());
        }

        DbConnect.closeBank();
        return list;
    }

    @Override
    public MedicalConsultation mapResultSetToEntity(ResultSet rs) throws Exception {
        int idMedicalCare = rs.getInt(1);
        int type = rs.getInt(2);
        Cpf cpfMedicalCare = new Cpf(rs.getString(3));
        Date dateMedicalCare = new Date(rs.getString(4));
        String reazon = rs.getString(5);

        return new MedicalConsultation(idMedicalCare, type, cpfMedicalCare, dateMedicalCare, reazon);
    }

}
