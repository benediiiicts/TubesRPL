package com.rpl.portal_ta.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.RoleDosen;

@Repository
public class RoleDosenRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert RoleDosen
    public void insertRoleDosen(RoleDosen roleDosen) {
        String sql = "INSERT INTO Role_Dosen (semester_id, role_id, nik) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, roleDosen.getSemesterId(), roleDosen.getRoleId(), roleDosen.getNikDosen());
    }

    // Get all RoleDosen
    public List<RoleDosen> getAllRoleDosen() {
        String sql = "SELECT * FROM Role_Dosen";
        return jdbcTemplate.query(sql, this::mapRowToRoleDosen);
    }
    
    public List<RoleDosen> getRoleDosen(int roleId, int semesterId) {
        String sql ="SELECT * FROM role_dosen WHERE role_id = ? AND semester_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToRoleDosen, roleId, semesterId);
    }

    private RoleDosen mapRowToRoleDosen(ResultSet resultSet, int rowNum) throws SQLException {
        return new RoleDosen(
            resultSet.getInt("semester_id"), 
            resultSet.getInt("role_id"), 
            resultSet.getString("nik"));
    }

}
