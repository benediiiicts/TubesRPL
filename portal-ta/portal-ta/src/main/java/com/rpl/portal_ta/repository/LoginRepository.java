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

    public Object findUser(String email) {
        // Query for 'Dosen'
        String sqlDosen = "SELECT * FROM Dosen WHERE email = ?";
        List<Dosen> dosenList = jdbcTemplate.query(sqlDosen, this::mapRowToDosen, email);
    
        // Query for 'Mahasiswa'
        String sqlMahasiswa = "SELECT * FROM Mahasiswa WHERE email = ?";
        List<Mahasiswa> mahasiswaList = jdbcTemplate.query(sqlMahasiswa, this::mapRowToMahasiswa, email);
    
        if (!dosenList.isEmpty()) {
            return dosenList.get(0);  // mengembnalikan dosen pertama yang ditemukan
        } else if (!mahasiswaList.isEmpty()) {
            return mahasiswaList.get(0);  // mengembnalikan mahasiswa pertama yang ditemukan
        } else {
            return null;  // email user tidak ditemukan di database
        }
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
