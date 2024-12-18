package com.rpl.portal_ta.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.Semester;
import com.rpl.portal_ta.data.Sidang;
import com.rpl.portal_ta.data.SidangPage;
import com.rpl.portal_ta.data.TA;

import jakarta.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SidangRepository {
    private final JdbcTemplate jdbcTemplate;

    public SidangRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Sidang sidang, HttpSession session){
        Semester curSemester = (Semester)session.getAttribute("semester");
        String sql = "INSERT INTO sidang (ta_id, nik_penguji1, nik_penguji2, tanggal, waktu, tempat, semester_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, sidang.getTaId(), sidang.getNikPenguji1(), sidang.getNikPenguji2(), 
        sidang.getTanggal(), sidang.getWaktu(), sidang.getTempat(), curSemester.getSemesterId());
    }

    public Sidang getOneSidang(int id_sidang){
        Sidang sidang;
        String sql = "SELECT * FROM sidang WHERE sidang_id = ?";
        sidang = jdbcTemplate.query(sql, this::mapRowToSidang, id_sidang).get(0);
        return sidang;
    }
    public List<SidangPage> getSidangPageData(int semesterId) {
        String sql = "SELECT " +
                "    Page_TA.topic AS \"Topic TA\", " +
                "    tanggal AS \"Tanggal\", " +
                "    waktu AS \"Waktu\", " +
                "    tempat AS \"Tempat\", " +
                "    Page_TA.\"Mahasiswa\", " +
                "    Page_TA.\"Dosen Pembimbing Utama\", " +
                "    Page_TA.\"Dosen Pembimbing Pendamping\", " +
                "    Dosen1.nama AS \"Dosen Ketua Penguji\", " +
                "    Dosen2.nama AS \"Dosen Anggota Penguji\" " +
                "FROM " +
                "    Sidang " +
                "    LEFT JOIN ( " +
                "        SELECT " +
                "            ta_id, " +
                "            semester_id, " +
                "            Mahasiswa.nama AS \"Mahasiswa\", " +
                "            Dosen1.nama AS \"Dosen Pembimbing Utama\", " +
                "            Dosen2.nama AS \"Dosen Pembimbing Pendamping\", " +
                "            topic " +
                "        FROM " +
                "            TA " +
                "            LEFT JOIN Mahasiswa ON TA.npm = Mahasiswa.npm " +
                "            LEFT JOIN Dosen AS Dosen1 ON TA.nik_pembimbing1 = Dosen1.nik " +
                "            LEFT JOIN Dosen AS Dosen2 ON TA.nik_pembimbing2 = Dosen2.nik " +
                "    ) AS Page_TA " +
                "    ON Sidang.ta_id = Page_TA.ta_id " +
                "    LEFT JOIN Dosen AS Dosen1 ON Sidang.nik_penguji1 = Dosen1.nik " +
                "    LEFT JOIN Dosen AS Dosen2 ON Sidang.nik_penguji2 = Dosen2.nik " +
                "WHERE " +
                "    Sidang.semester_id = ?";
        
                return jdbcTemplate.query(sql,this::mapRowToSidangPage);
            }
        
            // This method maps each row to a SidangPage object
    private SidangPage mapRowToSidangPage(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        SidangPage sidangPage = new SidangPage();
        sidangPage.setTopicTA(rs.getString("Topic TA"));
        sidangPage.setTanggal(rs.getString("Tanggal"));
        sidangPage.setWaktu(rs.getString("Waktu"));
        sidangPage.setTempat(rs.getString("Tempat"));
        sidangPage.setMahasiswa(rs.getString("Mahasiswa"));
        sidangPage.setDosenPembimbingUtama(rs.getString("Dosen Pembimbing Utama"));
        sidangPage.setDosenPembimbingPendamping(rs.getString("Dosen Pembimbing Pendamping"));
        sidangPage.setDosenKetuaPenguji(rs.getString("Dosen Ketua Penguji"));
        sidangPage.setDosenAnggotaPenguji(rs.getString("Dosen Anggota Penguji"));
        return sidangPage;
    }
    private Sidang mapRowToSidang(ResultSet resultSet, int rowNum) throws SQLException {
    return new Sidang(
        resultSet.getInt("sidang_id"), 
        resultSet.getInt("semester_id"), 
        resultSet.getInt("ta_id"), 
        resultSet.getString("nik_penguji1"), 
        resultSet.getString("nik_penguji2"), 
        resultSet.getDate("tanggal"),
        resultSet.getTime("waktu"),
        resultSet.getString("tempat"));
    }
}
