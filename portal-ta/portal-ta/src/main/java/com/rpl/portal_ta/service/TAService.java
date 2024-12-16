package com.rpl.portal_ta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rpl.portal_ta.data.TA;
import com.rpl.portal_ta.repository.TARepository;

@Service
public class TAService {

    @Autowired
    private TARepository TARepository;

    public void saveTugasAkhir(int semesterId, String topik, String npm, String nikPembimbing, String nikPembimbing2, int jenisTA) {
        // Simpan Tugas Akhir baru ke database
        TA ta = new TA();
        ta.setSemesterId(semesterId);
        ta.setTopic(topik);
        ta.setNpm(npm);
        ta.setNikPembimbing1(nikPembimbing2);
        ta.setNikPembimbing2(nikPembimbing2);
        ta.setJenisTA(jenisTA);
      

        TARepository.save(ta);  // Simpan ke database
    }
}
