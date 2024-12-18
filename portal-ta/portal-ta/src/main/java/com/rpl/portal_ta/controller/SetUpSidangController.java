package com.rpl.portal_ta.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpl.portal_ta.RequiredRole;
import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.data.TA;
import com.rpl.portal_ta.service.DosenService;
import com.rpl.portal_ta.service.SemesterService;
import com.rpl.portal_ta.service.SidangService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SetUpSidangController {
    
    @Autowired
    private DosenService dosenService;
    
    @Autowired
    private SidangService sidangService;

    @Autowired
    private SemesterService semesterService;
    
    @GetMapping("/sidang/add-new")
    @RequiredRole("koordinator")
    public String redirectSetUp(Model model, HttpSession session){
        List<TA> taList = sidangService.getTA(session);
        List<Dosen> dosenList = dosenService.getAllDosen();
        model.addAttribute("taList", taList);
        model.addAttribute("dosenList1", dosenList);
        model.addAttribute("dosenList2", dosenList);
        return "/SetUpSidang/SetUp";
    }

    @GetMapping("/sidang/add-new/input-staff")
    public String redirectInput(){
        return "/SetUpSidang/popUp/Popupinput";
    }

    @PostMapping("/koordinator/sidang/save")
    @RequiredRole("koordinator")
    public String saveTugasAkhir(
            @RequestParam(value = "tugas-akhir") int id_ta,
            @RequestParam(value = "penguji1") String nik,
            @RequestParam(value = "penguji2", required = false) String nik2,
            @RequestParam(value = "date-ta") Date date,
            @RequestParam(value = "time-ta") String timeString,
            @RequestParam(value = "tempat") String tempat, HttpSession session) {
        
        Time time = Time.valueOf(timeString + ":00");
        int semesterId = semesterService.getSemester().getSemesterId();
        sidangService.saveSidang(id_ta, nik, nik2, date, time, tempat, session);
        return "redirect:/koordinator/home/sidang";
    }
}
