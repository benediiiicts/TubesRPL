package com.rpl.portal_ta.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rpl.portal_ta.data.TA;

@Repository
public class TARepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(TA ta){
        String sql = "INSERT INTO TA (judul, npm, nik_pembimbing, nik_pembimbing2, jenis_ta) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ta.getTopic(), ta.getNpm(), ta.getNikPembimbing1(), ta.getNikPembimbing2(), ta.getJenisTA());
    }
}
