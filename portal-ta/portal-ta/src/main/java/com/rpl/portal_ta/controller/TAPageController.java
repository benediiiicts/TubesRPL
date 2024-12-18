package com.rpl.portal_ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.data.TA;
import com.rpl.portal_ta.service.MahasiswaService;
import com.rpl.portal_ta.service.TAService;

@Controller
public class TAPageController {

    @Autowired
    private TAService taService;

    @Autowired
    private MahasiswaService mahasiswaService;

    @GetMapping("/ta-page/{id}")
    public String getTADetails(@PathVariable("id") int id, Model model) {
        TA ta = taService.getTA(id);
        if (ta == null) {
            model.addAttribute("error", "Tugas Akhir not found.");
            return "error";
        }
        Mahasiswa mahasiswa = mahasiswaService.getMahasiswa(ta.getNpm());
        model.addAttribute("ta", ta);
        model.addAttribute("mahasiswa", mahasiswa);
        return "/DetailTA/DetailTA";
    }
}