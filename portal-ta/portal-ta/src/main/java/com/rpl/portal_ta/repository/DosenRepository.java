package com.rpl.portal_ta.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.Dosen;

@Repository
public class DosenRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addDosen(String nik, String nama, String email, String password){
        String sql = "INSERT INTO Dosen (nik, nama, email, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, nik, nama, email, password);
    }

    public List<Dosen> getAllDosen(){
        String sql = "SELECT * FROM Dosen";
        return jdbcTemplate.query(sql, this::mapRowToDosen);
    }

    private Dosen mapRowToDosen(ResultSet resultSet, int rowNum) throws SQLException {
        Dosen dosen = new Dosen();
        dosen.setNik(resultSet.getString("nik"));
        dosen.setNama(resultSet.getString("nama"));
        dosen.setEmail(resultSet.getString("email"));
        dosen.setPassword(resultSet.getString("password"));
        return dosen;

    }
}
