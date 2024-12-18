package com.rpl.portal_ta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpl.portal_ta.RequiredRole;
import com.rpl.portal_ta.data.Semester;
import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.data.Sidang;
import com.rpl.portal_ta.data.SidangTA;
import com.rpl.portal_ta.data.TA;
import com.rpl.portal_ta.service.HomeService;
import com.rpl.portal_ta.service.MahasiswaService;
import com.rpl.portal_ta.service.SemesterService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/") 
public class HomeController {
    
    @Autowired
    private HomeService homeService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private MahasiswaService mahasiswaService;

    private List<Semester> getSemesters() {
        return homeService.getSemesters();
    }
    private List<TA> getTA(){
        return homeService.getTA();
    }
    private TA getTA(Integer id_ta){
        return homeService.getTA(id_ta);
    }
    private List<Sidang> getSidang(){
        return homeService.getSidang();
    }
    @GetMapping("/filter-ta")
    @RequiredRole("*")
    public String filterTA(@RequestParam(value = "sort", required = false) Integer semester_id, Model model, HttpSession session){
        List<TA> tugasAkhir = this.getTA();
        List<TA> filteredTA = null;
        if(semester_id != null && semester_id > 0){
            if(session.getAttribute("role").equals("koordinator")){
                filteredTA = tugasAkhir.stream()
                        .filter(ta -> ta.getSemesterId() == semester_id)
                        .collect(Collectors.toList());
            }
            else if(session.getAttribute("role").equals("dosen")){
                Dosen dosen = (Dosen)session.getAttribute("user");
                filteredTA = tugasAkhir.stream()
                    .filter(ta -> ta.getSemesterId() == semester_id 
                    && (ta.getNikPembimbing1().equals(dosen.getNik())
                    || (ta.getNikPembimbing2() != null && ta.getNikPembimbing2().equals(dosen.getNik()))))
                    .collect(Collectors.toList());
            }
            else if(session.getAttribute("role").equals("mahasiswa")){
                Mahasiswa mahasiswa = (Mahasiswa) session.getAttribute("user");
                filteredTA = tugasAkhir.stream()
                    .filter(ta -> ta.getSemesterId() == semester_id 
                    && (ta.getNpm().equals(mahasiswa.getNpm())))
                    .collect(Collectors.toList());
            }
        }
        else {
            filteredTA = tugasAkhir;
        }
        List<SidangTA> sidangTA = new ArrayList<>();
        for(TA a : filteredTA){
            SidangTA temp = new SidangTA(null, a);
            temp.setMahasiswa(mahasiswaService.getMahasiswa(a.getNpm()));
            sidangTA.add(temp);
        }
        model.addAttribute("listSidangTA", sidangTA);
        model.addAttribute("semesters", getSemesters());
        if(session.getAttribute("role")!=null){
            if(session.getAttribute("role").equals("koordinator")){
                return "/MainPage/Koordinator/TA/home";
            }
            else if(session.getAttribute("role").equals("dosen")){
                return "/MainPage/Dosen/TA/home";
            }
            else{
                return "/MainPage/Mahasiswa/TA/home";
            }
        }
        return "/";
    }
    @GetMapping("/filter-sidang")
    public String filterSidang(@RequestParam(value = "sort", required = false) Integer semester_id, Model model, HttpSession session){
        List<Sidang> listSidang = this.getSidang();
        List<Sidang> filteredSidang = null;
        if(semester_id != null && semester_id > 0){
            if(session.getAttribute("role").equals("koordinator")){
                filteredSidang = listSidang.stream()
                        .filter(sidang -> sidang.getSemesterId() == semester_id)
                        .collect(Collectors.toList());
            }
            else if(session.getAttribute("role").equals("dosen")){
                Dosen dosen = (Dosen)session.getAttribute("user");
                filteredSidang = listSidang.stream()
                    .filter(sidang -> (sidang.getSemesterId() == semester_id) 
                    && (sidang.getNikPenguji1().equals(dosen.getNik())
                    || (sidang.getNikPenguji2() != null && sidang.getNikPenguji2().equals(dosen.getNik()))
                    || (dosen.getNik().equals(this.getTA(sidang.getTaId()).getNikPembimbing1()))
                    || (this.getTA(sidang.getTaId()).getNikPembimbing2()!= null) 
                    && this.getTA(sidang.getTaId()).getNikPembimbing2().equals(dosen.getNik())))
                    .collect(Collectors.toList());
            }
            else if(session.getAttribute("role").equals("mahasiswa")){
                Mahasiswa mahasiswa = (Mahasiswa) session.getAttribute("user");
                filteredSidang = listSidang.stream()
                    .filter(sidang -> sidang.getSemesterId() == semester_id 
                    && (this.getTA(sidang.getTaId()).getNpm().equals(mahasiswa.getNpm())))
                    .collect(Collectors.toList());
            }
        }
        else {
            filteredSidang = listSidang;
        }
        List<SidangTA> sidangTA = new ArrayList<>();
        for(Sidang s : filteredSidang){
            TA tempTA = this.getTA(s.getTaId());
            SidangTA temp = new SidangTA(s, tempTA);
            temp.setMahasiswa(mahasiswaService.getMahasiswa(tempTA.getNpm()));
            sidangTA.add(temp);
        }
        model.addAttribute("listSidangTA", sidangTA);
        model.addAttribute("semesters", getSemesters());
        if(session.getAttribute("role")!=null){
            if(session.getAttribute("role").equals("koordinator")){
                return "/MainPage/Koordinator/Sidang/home";
            }
            else if(session.getAttribute("role").equals("dosen")){
                return "/MainPage/Dosen/Sidang/home";
            }
            else{
                return "/MainPage/Mahasiswa/Sidang/home";
            }
        }
        return "/";
    }
    @GetMapping("/koordinator/home")
    @RequiredRole("koordinator")
    public String redirectKoor(Model model, HttpSession session){
        session.setAttribute("semester", semesterService.getSemester());
        model.addAttribute("semesters", getSemesters());
        return "redirect:/koordinator/home/sidang";
    }
    @GetMapping("/koordinator/home/sidang")
    @RequiredRole("koordinator")
    public String redirectKoorSidang(Model model, HttpSession session){
        session.setAttribute("semester", semesterService.getSemester());
        model.addAttribute("semesters", getSemesters());
        return "/MainPage/Koordinator/Sidang/home";
    }
    @GetMapping("/koordinator/home/tugas-akhir")
    @RequiredRole("koordinator")
    public String redirectKoorTA(Model model, HttpSession session){
        session.setAttribute("semester", semesterService.getSemester());
        model.addAttribute("semesters", getSemesters());
        return "/MainPage/Koordinator/TA/home";
    }
    @GetMapping("/dosen/home")
    @RequiredRole("dosen")
    public String redirectDosen(Model model, HttpSession session){
        session.setAttribute("semester", semesterService.getSemester());
        model.addAttribute("semesters", getSemesters());
        return "redirect:/dosen/home/sidang";
    }
    @GetMapping("/dosen/home/sidang")
    @RequiredRole("dosen")
    public String redirectDosenSidang(Model model, HttpSession session){
        session.setAttribute("semester", semesterService.getSemester());
        model.addAttribute("semesters", getSemesters());
        return "/MainPage/Dosen/Sidang/home";
    }
    @GetMapping("/dosen/home/tugas-akhir")
    @RequiredRole("dosen")
    public String redirectDosenTA(Model model, HttpSession session){
        session.setAttribute("semester", semesterService.getSemester());
        model.addAttribute("semesters", getSemesters());
        return "/MainPage/Dosen/TA/home";
    }
    @GetMapping("/mahasiswa/home")
    @RequiredRole("mahasiswa")
    public String redirectMahasiswa(Model model, HttpSession session){
        session.setAttribute("semester", semesterService.getSemester());
        model.addAttribute("semesters", getSemesters());
        return "redirect:/mahasiswa/home/sidang";
    }
    @GetMapping("/mahasiswa/home/sidang")
    @RequiredRole("mahasiswa")
    public String redirectMahasiswaSidang(Model model, HttpSession session){
        session.setAttribute("semester", semesterService.getSemester());
        model.addAttribute("semesters", getSemesters());
        return "/MainPage/Mahasiswa/Sidang/home";
    }
    @GetMapping("/mahasiswa/home/tugas-akhir")
    @RequiredRole("mahasiswa")
    public String redirectMahasiswaTA(Model model, HttpSession session){
        session.setAttribute("semester", semesterService.getSemester());
        model.addAttribute("semesters", getSemesters());
        return "/MainPage/Mahasiswa/TA/home";
    }
}
