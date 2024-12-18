package com.rpl.portal_ta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class BAPController {
    
    @GetMapping("/BAP")
    public String redirectBAP(HttpSession session, Model model){
        if(session.getAttribute("role").equals("koordinator"))
            return "/BAPPage/Koordinator/BAP";
        else if(session.getAttribute("role").equals("dosen"))
            return "/BAPPage/Penguji/BAP";
        else{
            return "/BAPPage/Mahasiswa/BAP";
        }
    }
}
