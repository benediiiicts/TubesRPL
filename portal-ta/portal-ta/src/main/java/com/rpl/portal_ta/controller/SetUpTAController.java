package com.rpl.portal_ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rpl.portal_ta.RequiredRole;
import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.service.MahasiswaService;


@Controller
public class SetUpTAController {

    @Autowired
    private MahasiswaService mahasiswaService;
    
    @GetMapping("/tugas-akhir/add-new")
    @RequiredRole("koordinator")
    public String tugasAkhirFormMahasiswa(Model model) {
        // Ambil daftar mahasiswa
        List<Mahasiswa> mahasiswaList = mahasiswaService.getAllMahasiswa();
        model.addAttribute("mahasiswaList", mahasiswaList);
        return "/SetUpTA/SetUpTA";  // Mengembalikan nama template Thymeleaf
    }
}
