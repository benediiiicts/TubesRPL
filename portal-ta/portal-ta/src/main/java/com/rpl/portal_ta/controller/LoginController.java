package com.rpl.portal_ta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "/Login/login";
    }
}
