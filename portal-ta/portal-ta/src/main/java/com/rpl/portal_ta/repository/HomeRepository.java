package com.rpl.portal_ta.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.Semester;
import com.rpl.portal_ta.data.Sidang;
import com.rpl.portal_ta.data.TA;

@Repository
public class HomeRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<Semester> getSemesters(){
        String sql = "SELECT * FROM Semester ORDER BY semester_id";
        List<Semester> semesterList = jdbcTemplate.query(sql, this::mapRowToSemester);
        return semesterList;
    }
    public List<Sidang> getSidang(){
        String sql = "SELECT * FROM Sidang";
        List<Sidang> sidangList = jdbcTemplate.query(sql, this::mapRowToSidang);
        return sidangList;
    }
    public List<TA> getTA(){
        String sql = "SELECT * FROM TA";
        List<TA> taList = jdbcTemplate.query(sql, this::mapRowToTA);
        return taList;
    }
    public TA getTA(Integer id_ta){
        String sql = "SELECT * FROM TA WHERE ta_id = ?";
        List<TA> taList = jdbcTemplate.query(sql, this::mapRowToTA, id_ta);
        return taList.get(0);
    }
    private Semester mapRowToSemester(ResultSet resultSet, int rowNum) throws SQLException {
        return new Semester(
            resultSet.getInt("semester_id"), 
            resultSet.getString("tahun_ajaran"), 
            resultSet.getString("periode"));
    }
    private Sidang mapRowToSidang(ResultSet resultSet, int rowNum) throws SQLException {
        return new Sidang(
            resultSet.getInt("sidang_id"), 
            resultSet.getInt("semester_id"), 
            resultSet.getInt("ta_id"), 
            resultSet.getString("nik_penguji1"), 
            resultSet.getString("nik_penguji2"), 
            resultSet.getDate("tanggal"),
            resultSet.getTime("waktu"),
            resultSet.getString("tempat"));
    }
    private TA mapRowToTA(ResultSet resultSet, int rowNum) throws SQLException {
        return new TA(
            resultSet.getInt("ta_id"), 
            resultSet.getInt("semester_id"), 
            resultSet.getString("npm"), 
            resultSet.getString("nik_pembimbing1"), 
            resultSet.getString("nik_pembimbing2"),  
            resultSet.getString("topic"));
    }
}
