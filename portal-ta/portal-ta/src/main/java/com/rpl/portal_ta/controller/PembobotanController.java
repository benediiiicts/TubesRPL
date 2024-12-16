package com.rpl.portal_ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpl.portal_ta.RequiredRole;
import com.rpl.portal_ta.data.Bobot;
import com.rpl.portal_ta.service.BobotService;
import com.rpl.portal_ta.service.SemesterService;

// import jakarta.servlet.http.HttpSession;

@Controller
public class PembobotanController {

    @Autowired
    private BobotService bobotService;

    @Autowired
    private SemesterService semesterService;
    
    // @Autowired
    // private HttpSession session;
 

    @GetMapping("/pembobotan")
    @RequiredRole("koordinator")
    public String showPembobotanForm(Model model) {
        int semesterId = semesterService.getSemester().getSemesterId();
        // Ambil data bobot berdasarkan semesterId dan roleId
        List<Bobot> komponenKoordinator = bobotService.getKomponenByRole(semesterId, 1); // Contoh: semesterId = 1, roleId = 1 (Koordinator)
        List<Bobot> komponenPenguji = bobotService.getKomponenByRole(semesterId, 2); // Contoh: semesterId = 1, roleId = 2 (Penguji)
        List<Bobot> komponenPembimbing = bobotService.getKomponenByRole(semesterId, 3); // Contoh: semesterId = 1, roleId = 3 (Pembimbing)

        // Kirim data ke Thymeleaf
        model.addAttribute("komponenPenguji", komponenPenguji);
        model.addAttribute("komponenPembimbing", komponenPembimbing);
        model.addAttribute("komponenKoordinator", komponenKoordinator);

        return "bobotTest"; // Nama file template Thymeleaf
    }


    /*
     * Method untuk menambah komponen
     */

    //nambah komponen nilai untuk pembimbing
    @PostMapping("/pembobotan/add-komponen-pembimbing")
    @RequiredRole("koordinator")
    public String addKomponenPembimbing(@RequestParam("komponen") String komponen, 
                                @RequestParam("persentase") int persentase,
                                @RequestParam("addKomponen") int addKomponenToRoleId){
        int semester_id = semesterService.getSemester().getSemesterId();
        int role_id = addKomponenToRoleId;

        bobotService.tambahKomponen(semester_id, role_id, komponen, persentase);
        return "redirect:/pembobotan";
    }
    
    //nambah komponen nilai untuk penguji
    @PostMapping("/pembobotan/add-komponen-penguji")
    @RequiredRole("koordinator")
    public String addKomponenPenguji(@RequestParam("komponen") String komponen, 
                                @RequestParam("persentase") int persentase,
                                @RequestParam("addKomponen") int addKomponenToRoleId){
        int semester_id = semesterService.getSemester().getSemesterId();
        int role_id = addKomponenToRoleId;

        bobotService.tambahKomponen(semester_id, role_id, komponen, persentase);
        return "redirect:/pembobotan";
    }

    //nambah komponen nilai untuk koordinator
    @PostMapping("/pembobotan/add-komponen-koordinator")
    @RequiredRole("koordinator")
    public String addKomponenKoordinator(@RequestParam("komponen") String komponen, 
                                @RequestParam("persentase") int persentase,
                                @RequestParam("addKomponen") int addKomponenToRoleId){
        int semester_id = semesterService.getSemester().getSemesterId();
        int role_id = addKomponenToRoleId;

        bobotService.tambahKomponen(semester_id, role_id, komponen, persentase);
        return "redirect:/pembobotan";
    }
}
