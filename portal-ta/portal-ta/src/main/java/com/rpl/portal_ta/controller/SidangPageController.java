package com.rpl.portal_ta.controller;

import java.net.http.HttpClient;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.data.Sidang;
import com.rpl.portal_ta.data.TA;
import com.rpl.portal_ta.service.DosenService;
import com.rpl.portal_ta.service.MahasiswaService;
import com.rpl.portal_ta.service.SidangService;
import com.rpl.portal_ta.service.TAService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SidangPageController {
    
    @Autowired
    private SidangService sidangService;

    @Autowired
    private MahasiswaService mahasiswaService;

    @Autowired
    private DosenService dosenService;
    
    @Autowired
    private TAService taService;

    @GetMapping("/sidang/{id}")
    public String getSidangDetails(@PathVariable("id") int id, Model model, HttpSession session) {
        Sidang sidang = sidangService.getSidang(id);
        TA ta = taService.getTA(sidang.getTaId());
        if (ta == null) {
            model.addAttribute("error", "Tugas Akhir not found.");
            return "error";
        }
        Mahasiswa mahasiswa = mahasiswaService.getMahasiswa(ta.getNpm());
        Dosen pembimbing1 = dosenService.getDosen(ta.getNikPembimbing1());
        Dosen pembimbing2 = new Dosen();
        pembimbing2.setEmail("");
        pembimbing2.setNama("");
        pembimbing2.setNik("");
        pembimbing2.setPassword("");
        if(ta.getNikPembimbing2()!=null)
            pembimbing2 = dosenService.getDosen(ta.getNikPembimbing2());
        Dosen penguji1 = dosenService.getDosen(sidang.getNikPenguji1());
        Dosen penguji2 = new Dosen();
        penguji2.setEmail("");
        penguji2.setNama("");
        penguji2.setNik("");
        penguji2.setPassword("");
        if(sidang.getNikPenguji2()!=null)
            penguji2 = dosenService.getDosen(sidang.getNikPenguji2());
        model.addAttribute("ta", ta);
        model.addAttribute("sidang", sidang);
        model.addAttribute("mahasiswa", mahasiswa);
        model.addAttribute("pembimbing1", pembimbing1);
        model.addAttribute("pembimbing2", pembimbing2);
        model.addAttribute("penguji1", penguji1);
        model.addAttribute("penguji2", penguji2);
        // if(LocalDate.now().isBefore(sidang.getTanggal().toLocalDate())
        //     && LocalTime.now().isBefore(sidang.getWaktu().toLocalTime())){
            if(session.getAttribute("role").equals("koordinator"))
                return "/SidangMainPage_sebelumSidang/Koordinator/TApageOverview";
            else if(session.getAttribute("role").equals("dosen"))
                return "/SidangMainPage_sebelumSidang/Dosen/TApageOverview";
            else if(session.getAttribute("role").equals("mahasiswa"))
                return "/SidangMainPage_sebelumSidang/Mahasiswa/TApageOverview";
            else
                return "/";
        // }
        // else{
        //     if(session.getAttribute("role").equals("koordinator"))
        //         return "/SidangMainPage/Koordinator/TApageOverview";
        //     else if(session.getAttribute("role").equals("dosen"))
        //         return "/SidangMainPage/Dosen/TApageOverview";
        //     else if(session.getAttribute("role").equals("mahasiswa"))
        //         return "/SidangMainPage/Mahasiswa/TApageOverview";
        //     else
        //         return "/";
        // }
    }
}
