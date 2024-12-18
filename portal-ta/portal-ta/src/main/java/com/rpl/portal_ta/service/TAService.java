package com.rpl.portal_ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpl.portal_ta.data.Semester;
import com.rpl.portal_ta.data.TA;
import com.rpl.portal_ta.repository.TARepository;

import jakarta.servlet.http.HttpSession;

@Service
public class TAService {

    @Autowired
    private TARepository TARepository;

    public void saveTugasAkhir(int semesterId, String topik, String npm, String nikPembimbing, String nikPembimbing2, int tipe, HttpSession session) {
        // Simpan Tugas Akhir baru ke database
        TA ta = new TA();
        ta.setSemesterId(semesterId);
        ta.setTopic(topik);
        ta.setNpm(npm);
        ta.setNikPembimbing1(nikPembimbing2);
        ta.setNikPembimbing2(nikPembimbing2);
        ta.setTipe(tipe);
      

        TARepository.save(ta, session);
    }
    public List<TA> getCurrTA(HttpSession session){
        Semester curr = (Semester)session.getAttribute("semester");
        return TARepository.getTA(curr.getSemesterId());
    }
    public TA getTA(int id_ta){
        return TARepository.getOneTA(id_ta);
    }
}
