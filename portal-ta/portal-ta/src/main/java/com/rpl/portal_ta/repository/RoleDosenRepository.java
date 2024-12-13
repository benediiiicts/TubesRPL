package com.rpl.portal_ta.repository;

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
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            RoleDosen roleDosen = new RoleDosen();
            roleDosen.setSemesterId(rs.getInt("semester_id"));
            roleDosen.setRoleId(rs.getInt("role_id"));
            roleDosen.setNikDosen(rs.getString("nik"));
            return roleDosen;
        });
    }


}
