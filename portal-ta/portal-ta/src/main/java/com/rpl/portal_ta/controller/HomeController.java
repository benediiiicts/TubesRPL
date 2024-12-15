package com.rpl.portal_ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rpl.portal_ta.RequiredRole;
import com.rpl.portal_ta.service.HomeService;

@Controller
public class HomeController {
    
    @Autowired
    private HomeService homeService;

    @GetMapping("/koordinator/home")
    @RequiredRole("koordinator")
    public String redirectKoor(){
        return "redirect:/koordinator/home/sidang";
    }
    @GetMapping("/koordinator/home/sidang")
    @RequiredRole("koordinator")
    public String redirectKoorSidang(){
        return "/MainPage/Koordinator/Sidang/home";
    }
    @GetMapping("/koordinator/home/tugas-akhir")
    @RequiredRole("koordinator")
    public String redirectKoorTA(){
        return "/MainPage/Koordinator/TA/home";
    }
    @GetMapping("/dosen/home")
    @RequiredRole("dosen")
    public String redirectDosen(){
        return "/MainPage/Dosen/home";
    }
    @GetMapping("/mahasiswa/home")
    @RequiredRole("mahasiswa")
    public String redirectMahasiswa(){
        return "/MainPage/Mahasiswa/home";
    }
}
