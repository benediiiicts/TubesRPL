package com.rpl.portal_ta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rpl.portal_ta.RequiredRole;

@Controller
public class SetUpTAController {
    
    @GetMapping("/tugas-akhir/add-new")
    @RequiredRole("koordinator")
    public String redirectSetUp(){
        return "/SetUpTA/SetUpTA";
    }
}
