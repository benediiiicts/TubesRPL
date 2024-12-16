package com.rpl.portal_ta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.repository.MahasiswaRepository;

@Service
public class MahasiswaService {
    
    @Autowired
    MahasiswaRepository mahasiswaRepository;

    public Mahasiswa getMahasiswa(String npm){
        return mahasiswaRepository.getMahasiswa(npm);
    }
}
