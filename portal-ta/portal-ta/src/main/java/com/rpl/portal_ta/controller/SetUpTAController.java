package com.rpl.portal_ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpl.portal_ta.RequiredRole;
import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.service.DosenService;
import com.rpl.portal_ta.service.MahasiswaService;
import com.rpl.portal_ta.service.SemesterService;
import com.rpl.portal_ta.service.TAService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SetUpTAController {

    @Autowired
    private MahasiswaService mahasiswaService;

    @Autowired
    private DosenService dosenService;
    
    @Autowired
    private TAService taService;

    @Autowired
    private SemesterService semesterService;

    @GetMapping("/tugas-akhir/add-new")
    @RequiredRole("koordinator")
    public String tugasAkhirFormMahasiswa(Model model) {
        List<Mahasiswa> mahasiswaList = mahasiswaService.getAllMahasiswa();
        List<Dosen> dosenList = dosenService.getAllDosen();
        model.addAttribute("mahasiswaList", mahasiswaList);
        model.addAttribute("dosenList1", dosenList);
        model.addAttribute("dosenList2", dosenList);

        return "/SetUpTA/SetUpTA";
    }

    @PostMapping("/koordinator/tugas-akhir/save")
    @RequiredRole("koordinator")
    public String saveTugasAkhir(
            @RequestParam(value = "judul") String judul,
            @RequestParam(value = "mahasiswa") String npm,
            @RequestParam(value = "pembimbing-1") String nikPembimbing,
            @RequestParam(value = "pembimbing-2", required = false) String nikPembimbing2,
            @RequestParam(value = "jenis") int jenisTA, HttpSession session) {
        
        int semesterId = semesterService.getSemester().getSemesterId();
        
        taService.saveTugasAkhir(semesterId, judul, npm, nikPembimbing, nikPembimbing2, jenisTA, session);
        return "redirect:/koordinator/home/tugas-akhir";
    }
}