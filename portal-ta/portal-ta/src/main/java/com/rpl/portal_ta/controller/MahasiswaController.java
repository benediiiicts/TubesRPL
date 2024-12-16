// package com.rpl.portal_ta.controller;

// import com.rpl.portal_ta.data.Mahasiswa;
// import com.rpl.portal_ta.service.MahasiswaService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;

// import java.util.List;

// @Controller
// public class MahasiswaController {

//     @Autowired
//     private MahasiswaService mahasiswaService;

//     // Endpoint untuk menampilkan halaman tambah Tugas Akhir dengan data mahasiswa di dropdown
//     @GetMapping("/tugas-akhir/add-new")
//     public String tugasAkhirFormMahasiswa(Model model) {
//         // Ambil daftar mahasiswa
//         List<Mahasiswa> mahasiswaList = mahasiswaService.getAllMahasiswa();
//         model.addAttribute("mahasiswaList", mahasiswaList);
//         return "/SetUpTA/SetUpTA";  // Mengembalikan nama template Thymeleaf
//     }
// }
