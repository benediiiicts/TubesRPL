package com.rpl.portal_ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpl.portal_ta.RequiredRole;
import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.service.DosenService;
import com.rpl.portal_ta.service.MahasiswaService;
import com.rpl.portal_ta.service.SemesterService;
import com.rpl.portal_ta.service.TAService;


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
        // Ambil daftar mahasiswa
        List<Mahasiswa> mahasiswaList = mahasiswaService.getAllMahasiswa();
        List<Dosen> dosenList = dosenService.getAllDosen();
        model.addAttribute("mahasiswaList", mahasiswaList);
        model.addAttribute("dosenList1", dosenList);
        model.addAttribute("dosenList2", dosenList);

        return "/SetUpTA/SetUpTA";  // Mengembalikan nama template Thymeleaf
    }

    @PostMapping("/tugas-akhir/save")
    @RequiredRole("koordinator")
    public String saveTugasAkhir(@RequestParam("judul") String judul,
                                 @RequestParam("mahasiswa") String npm, // menerima npm mahasiswa
                                 @RequestParam("dosen") String nikPembimbing,
                                 @RequestParam("dosen2") String nikPembimbing2,
                                 @RequestParam("jenis") int jenisTA) {
        int semesterId = semesterService.getSemester().getSemesterId();
        
        taService.saveTugasAkhir(semesterId, judul, npm, nikPembimbing, nikPembimbing2, jenisTA);
        return "redirect:/tugas-akhir";  // Redirect ke halaman daftar tugas akhir
    }
}
