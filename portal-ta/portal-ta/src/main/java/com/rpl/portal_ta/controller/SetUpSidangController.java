package com.rpl.portal_ta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SetUpSidangController {
    
    @GetMapping("/sidang/add-new")
    public String redirectSetUp(){
        return "/SetUpSidang/SetUp";
    }
    @GetMapping("/sidang/add-new/input-staff")
    public String redirectInput(){
        return "/SetUpSidang/popUp/Popupinput";
    }
}
