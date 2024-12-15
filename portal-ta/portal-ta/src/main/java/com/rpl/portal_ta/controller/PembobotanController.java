package com.rpl.portal_ta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rpl.portal_ta.RequiredRole;

@Controller
public class PembobotanController {
    
    @GetMapping("/pembobotan")
    @RequiredRole("koordinator")
    public String redirectPembobotan(){
        return "/Pembobotan/pembobotan";
    }
}
