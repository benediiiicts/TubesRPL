package com.rpl.portal_ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpl.portal_ta.data.Bobot;
import com.rpl.portal_ta.repository.BobotRepository;

@Service
public class BobotService {
    
    @Autowired
    private BobotRepository bobotRepository;

    public void tambahKomponen(int semester_id, int role_id, String komponen, int persentase){
        bobotRepository.tambahKomponen(semester_id, role_id, komponen, persentase);
    }

    public List<Bobot> getKomponenByRole(int semesterId, int roleId) {
        List<Bobot> listBobot = new ArrayList<>();
        listBobot = bobotRepository.getKomponenByRole(semesterId, roleId);
        return listBobot;
    }

}
