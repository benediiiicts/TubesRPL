package com.rpl.portal_ta.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpl.portal_ta.data.Sidang;
import com.rpl.portal_ta.data.TA;
import com.rpl.portal_ta.repository.SidangRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class SidangService {
    
    @Autowired
    SidangRepository sidangRepository;
    
    @Autowired
    TAService taService;

    public void saveSidang(int id_ta, String nik, String nik2, Date date, Time time, String tempat, HttpSession session){
        Sidang sidang = new Sidang();
        sidang.setTaId(id_ta);
        sidang.setNikPenguji1(nik);
        sidang.setNikPenguji2(nik2);
        sidang.setTanggal(date);
        sidang.setWaktu(time);
        sidang.setTempat(tempat);

        sidangRepository.save(sidang, session);
    }
    public List<TA> getTA(HttpSession session){
        List<TA> ta = taService.getCurrTA(session);
        return ta;
    }
}
