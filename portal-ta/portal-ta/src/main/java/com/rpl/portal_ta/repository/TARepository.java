package com.rpl.portal_ta.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.Semester;
import com.rpl.portal_ta.data.TA;

import jakarta.servlet.http.HttpSession;

@Repository
public class TARepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(TA ta, HttpSession session){
        Semester curSemester = (Semester)session.getAttribute("semester");
        String sql = "INSERT INTO TA (topic, npm, nik_pembimbing1, nik_pembimbing2, tipe, semester_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ta.getTopic(), ta.getNpm(), ta.getNikPembimbing1(), ta.getNikPembimbing2(), ta.getTipe(), curSemester.getSemesterId());
    }
    public List<TA> getTA(Integer semester_id){
        String sql = "SELECT * FROM TA WHERE semester_id = ?";
        List<TA> listTA = jdbcTemplate.query(sql, this::mapRowToTA, semester_id);
        return listTA;
    }
    public TA getOneTA(int id_ta){
        String sql = "SELECT * FROM TA WHERE ta_id = ?";
        TA ta = jdbcTemplate.query(sql, this::mapRowToTA, id_ta).get(0);
        return ta;
    }
    private TA mapRowToTA(ResultSet resultSet, int rowNum) throws SQLException {
        return new TA(
            resultSet.getInt("ta_id"), 
            resultSet.getInt("semester_id"), 
            resultSet.getString("npm"), 
            resultSet.getString("nik_pembimbing1"), 
            resultSet.getString("nik_pembimbing2"), 
            resultSet.getString("topic"), 
            resultSet.getInt("tipe"));
    }
}
