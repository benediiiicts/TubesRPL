package com.rpl.portal_ta.repository;

import com.rpl.portal_ta.data.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MahasiswaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Query untuk mengambil semua mahasiswa
    // public List<Mahasiswa> getAllMahasiswa() {
    //     String sql = "SELECT npm, nama, email FROM mahasiswa";  // Ubah nama tabel sesuai dengan yang Anda punya
    //     return jdbcTemplate.query(sql, (rs, rowNum) ->
    //         new Mahasiswa(
    //             rs.getString("npm"),
    //             rs.getString("nama"),
    //             rs.getString("email"),
    //             null  // Anda bisa menambahkan password jika perlu
    //         )
    //     );
    // }
    public List<Mahasiswa> getAllMahasiswa() {
        String sql = "SELECT * FROM mahasiswa";  // Ubah nama tabel sesuai dengan yang Anda punya
        return jdbcTemplate.query(sql, this::mapRowToMahasiswa);
    }
    
    private Mahasiswa mapRowToMahasiswa(ResultSet rs, int rowNum) throws SQLException {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNpm(rs.getString("npm"));
        mahasiswa.setNama(rs.getString("nama"));
        mahasiswa.setEmail(rs.getString("email"));
        return mahasiswa;
    }

}
