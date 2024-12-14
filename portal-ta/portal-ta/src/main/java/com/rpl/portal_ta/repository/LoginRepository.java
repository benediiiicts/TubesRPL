package com.rpl.portal_ta.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.data.RoleDosen;
import com.rpl.portal_ta.data.Roles;
import com.rpl.portal_ta.data.Semester;

@Repository
public class LoginRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private RoleDosenRepository roleDosenRepository;

    public Object findUser(String email, String password) {
        // Query for 'Dosen'
        String sqlDosen = "SELECT * FROM Dosen WHERE email = ? and password = ?";
        List<Dosen> dosenList = jdbcTemplate.query(sqlDosen, this::mapRowToDosen, email, password);
    
        // Query for 'Mahasiswa'
        String sqlMahasiswa = "SELECT * FROM Mahasiswa WHERE email = ? and password = ?";
        List<Mahasiswa> mahasiswaList = jdbcTemplate.query(sqlMahasiswa, this::mapRowToMahasiswa, email, password);
    
        if (!dosenList.isEmpty()) {
            return dosenList.get(0);  // mengembnalikan dosen pertama yang ditemukan
        } else if (!mahasiswaList.isEmpty()) {
            return mahasiswaList.get(0);  // mengembnalikan mahasiswa pertama yang ditemukan
        } else {
            return null;  // email user tidak ditemukan di database
        }
    }
    public boolean isKoor(Dosen dosen){
        //cari semester sekarang (terbaru)
        String sql = "SELECT * FROM Semester ORDER BY tahun_ajaran DESC LIMIT 1";
        List<Semester> semesterList = jdbcTemplate.query(sql, this::mapRowToSemester);
        Semester currSemester = semesterList.get(0);
        //ambil id koordinator
        sql = "SELECT * FROM Roles WHERE role_name = ?";
        List<Roles> rolesList = jdbcTemplate.query(sql, this::mapRowToRoles, "Koordinator");
        Roles role = rolesList.get(0);
        //cek role semester ini
        List<RoleDosen> roleDosen = roleDosenRepository.getRoleDosen(role.getRoleId(), currSemester.getSemesterId());
        if(roleDosen.isEmpty()) return false;
        else{
            //cek apakah dosen adalah koordinator
            RoleDosen currRoleDosen = roleDosen.get(0);
            if(currRoleDosen.getNikDosen().equals(dosen.getNik()))
                return true;
            else
                return false;
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
    private Semester mapRowToSemester(ResultSet resultSet, int rowNum) throws SQLException {
        return new Semester(
            resultSet.getInt("semester_id"), 
            resultSet.getString("tahun_ajaran"), 
            resultSet.getString("periode"));
    }
    private Roles mapRowToRoles(ResultSet resultSet, int rowNum) throws SQLException {
        return new Roles(
            resultSet.getInt("role_id"), 
            resultSet.getString("role_name"));
    }
}
