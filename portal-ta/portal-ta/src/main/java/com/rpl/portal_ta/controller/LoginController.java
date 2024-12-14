package com.rpl.portal_ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private HttpSession session;

    @GetMapping("/")
    public String redirectToLogin() {
        return "/Login/login";
    }
    @PostMapping("/")
    public String postLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model){
        Mahasiswa mahasiswa;
        Dosen dosen;

        try {
            Object user = loginService.findUser(email, password);  
            if (user != null) {
                if (user instanceof Dosen) {
                    dosen = (Dosen) user;
                    session.setAttribute("user", dosen);
                    session.setAttribute("nama", dosen.getNama());
                    if(loginService.isKoor(dosen)){
                        session.setAttribute("role", "koordinator");
                        return "redirect:/koordinator/home";
                    }
                    else{
                        session.setAttribute("role", "dosen");
                        return "redirect:/dosen/home";
                    }
                } else if (user instanceof Mahasiswa) {
                    mahasiswa = (Mahasiswa) user;
                    session.setAttribute("user", mahasiswa);
                    session.setAttribute("nama", mahasiswa.getNama());
                    session.setAttribute("role", "mahasiswa");
                    return "redirect:/mahasiswa/home";
                }
            } else {
                model.addAttribute("email", email);
                model.addAttribute("password", password);
                model.addAttribute("error", "User not found");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "/Login/login";
    }

    @GetMapping("/Dosen")
    public String loginSuccess() {
        return "/Login/loginSuccess";
    }
}
