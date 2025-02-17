package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.project.model.Doctor;

public class DoctorDAO extends AbstractDAO<Doctor, Integer> {
    @Override
    public void start() {
        DbConnect.openBank();
        String query = "";
        query += "CREATE TABLE IF NOT EXISTS " + this.getTableName() + " (";
        query += "doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        query += "name TEXT NOT NULL";
        query += ");";

        try {
            DbConnect.execQuery(query);
        } catch (Exception e) {
            System.err.println("Error MedicalAppointmentDAO: " + e.getMessage());
        }
        DbConnect.closeBank();
    }

    @Override
    public Doctor mapResultSetToEntity(ResultSet rs) throws Exception {
        int id = rs.getInt("doctor_id");
        String name = rs.getString("name");

        return new Doctor(id, name);
    }

    @Override
    public String getTableName() {
        return "doctor";
    }

    @Override
    protected String getSaveQuery() {
        String query = "";
        query += "INSERT INTO " + this.getTableName() + " ";
        query += "(name)";
        query += "VALUES(?)";
        return query;
    }

    @Override
    protected void setPstmtForSave(PreparedStatement pstmt, Doctor obj) throws SQLException {
        pstmt.setString(1, obj.getName());
    }

    @Override
    protected String getUpdateQuery() {
        String query = "";
        query += "UPDATE " + this.getTableName() + " SET ";
        query += "name = ? ";
        query += "WHERE doctor_id = ?;";
        return query;
    }

    @Override
    protected void setPstmtForUdate(PreparedStatement pstmt, Doctor obj) throws SQLException {
        pstmt.setString(1, obj.getName());
    }

}
