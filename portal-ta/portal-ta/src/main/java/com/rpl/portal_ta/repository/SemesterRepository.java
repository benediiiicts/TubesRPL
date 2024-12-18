package com.rpl.portal_ta.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.Semester;

@Repository
public class SemesterRepository {
    @Autowired    
    JdbcTemplate jdbcTemplate;

    public Semester getSemester(){
        String sql = "SELECT * FROM Semester ORDER BY semester_id LIMIT 1";
        List<Semester> hasilQuerySemester = jdbcTemplate.query(sql, this::mapRowToSemester);
        return hasilQuerySemester.get(0);
    }

    public void addSemester(Semester semester){
        String sql = "INSERT INTO Semester (tahun_ajaran, periode) VALUES (?, ?)";
        jdbcTemplate.update(sql, semester.getTahunAjaran(), semester.getPeriode());
    }

    private Semester mapRowToSemester(ResultSet rs, int rowNum) throws SQLException {
        Semester semester = new Semester();
        semester.setSemesterId(rs.getInt("semester_id"));
        semester.setTahunAjaran(rs.getString("tahun_ajaran"));
        semester.setPeriode(rs.getString("periode"));
        return semester;
    }

}
