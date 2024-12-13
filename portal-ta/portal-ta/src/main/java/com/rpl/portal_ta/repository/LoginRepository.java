package com.rpl.portal_ta.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.data.Mahasiswa;

@Repository
public class LoginRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //cek akun dosen/ mahasiswa
    public String getUser(String email){
        String sql = "SELECT * FROM Dosen WHERE email = ?";
        List<Dosen> dosenList = jdbcTemplate.query(sql, this::mapRowToDosen, email);
        List<Mahasiswa> mahasiswaList;
        if(dosenList.isEmpty()){
            mahasiswaList = jdbcTemplate.query(sql, this::mapRowToMahasiswa, email);
            if(mahasiswaList.isEmpty()){
                return null;
            }
            return "mahasiswa";
        }
        return "dosen";
    }
    private Dosen mapRowToDosen(ResultSet resultSet, int rowNum) throws SQLException {
        return new Dosen(
            resultSet.getString("nik"), 
            resultSet.getString("nama"), 
            resultSet.getString("email"), 
            resultSet.getString("password"));
    }
    private Mahasiswa mapRowToMahasiswa(ResultSet resultSet, int rowNum) throws SQLException {
        return new Mahasiswa(
            resultSet.getString("npm"), 
            resultSet.getString("nama"), 
            resultSet.getString("email"), 
            resultSet.getString("password"));
    }
}
