package com.rpl.portal_ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "/Login/login";
    }
    @PostMapping("/")
    public String postLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model){
        return "";
    }
}
