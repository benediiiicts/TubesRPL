package com.rpl.portal_ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.repository.DosenRepository;

@Service
public class DosenService {
    
    @Autowired
    private DosenRepository dosenRepository;

    // public void addDosen(String nik, String nama, String email, String password){
    //     Dosen dosen = new Dosen();
    //     dosen.setNik(nik);
    //     dosen.setNama(nama);
    //     dosen.setEmail(email);
    //     dosen.setPassword(password);
        
    //     dosenRepository.addDosen(dosen);
    // }

    public List<Dosen> getAllDosen(){
        return dosenRepository.getAllDosen();
    }
}
