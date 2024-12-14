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
        return "/MainPage/Koordinator/home";
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
