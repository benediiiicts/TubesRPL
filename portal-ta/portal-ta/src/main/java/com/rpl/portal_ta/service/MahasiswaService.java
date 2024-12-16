package com.rpl.portal_ta.service;

import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MahasiswaService {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    // Ambil semua data mahasiswa
    public List<Mahasiswa> getAllMahasiswa() {
        return mahasiswaRepository.getAllMahasiswa();
    }
}
