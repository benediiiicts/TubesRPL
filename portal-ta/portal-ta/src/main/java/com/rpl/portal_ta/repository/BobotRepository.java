package com.rpl.portal_ta.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.Bobot;

@Repository
public class BobotRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void tambahKomponen(int semester_id, int role_id, String komponen, int persentase){
        String sql = "INSERT INTO Bobot (semester_id, role_id, komponen, persentase) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, semester_id, role_id, komponen, persentase);
    }

    public List<Bobot> getKomponenByRole(int semesterId, int roleId) {
        String sql = "SELECT * FROM Bobot WHERE semester_id = ? AND role_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToBobot, semesterId, roleId);            
    }

    private Bobot mapRowToBobot(ResultSet rs, int rowNum) throws SQLException {
        Bobot bobot = new Bobot();
        bobot.setKomponen(rs.getString("komponen"));
        bobot.setPersentase(rs.getInt("persentase"));
        return bobot;
    }
}
