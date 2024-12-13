package com.rpl.portal_ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpl.portal_ta.service.DosenService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/Dosen")
public class DosenController {
    
    @Autowired
    private HttpSession session;

    @Autowired
    private DosenService dosenService;
    
    @GetMapping("/Dashboard")
    public String dashboard(){
        return "/Dosen/dashboard";
    }

}
