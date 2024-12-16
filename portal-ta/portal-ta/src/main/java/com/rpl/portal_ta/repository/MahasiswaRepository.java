package com.rpl.portal_ta.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.Mahasiswa;

@Repository
public class MahasiswaRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Mahasiswa getMahasiswa(String npm){
        String sql = "SELECT * FROM Mahasiswa WHERE npm = ?";
        List<Mahasiswa> listMahasiswa = jdbcTemplate.query(sql, this::mapRowToMahasiswa, npm);
        return listMahasiswa.get(0);
    }

    public List<Mahasiswa> getAllMahasiswa(){
        String sql = "SELECT * FROM Mahasiswa";
        List<Mahasiswa> listMahasiswa = jdbcTemplate.query(sql, this::mapRowToMahasiswa);
        return listMahasiswa;
    }
    private Mahasiswa mapRowToMahasiswa(ResultSet resultSet, int rowNum) throws SQLException {
        return new Mahasiswa(
            resultSet.getString("npm"), 
            resultSet.getString("nama"), 
            resultSet.getString("email"), 
            resultSet.getString("password"));
    }
}
