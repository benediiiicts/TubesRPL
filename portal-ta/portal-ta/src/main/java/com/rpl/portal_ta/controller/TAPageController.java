package com.rpl.portal_ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.data.TA;
import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.service.DosenService;
import com.rpl.portal_ta.service.MahasiswaService;
import com.rpl.portal_ta.service.TAService;

@Controller
public class TAPageController {

    @Autowired
    private TAService taService;

    @Autowired
    private MahasiswaService mahasiswaService;

    @Autowired
    private DosenService dosenService;

    @GetMapping("/ta-page/{id}")
    public String getTADetails(@PathVariable("id") int id, Model model) {
        TA ta = taService.getTA(id);
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
        model.addAttribute("ta", ta);
        model.addAttribute("mahasiswa", mahasiswa);
        model.addAttribute("pembimbing1", pembimbing1);
        model.addAttribute("pembimbing2", pembimbing2);
        return "/DetailTA/DetailTA";
    }
}