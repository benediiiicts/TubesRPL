package com.rpl.portal_ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpl.portal_ta.data.Semester;
import com.rpl.portal_ta.data.Sidang;
import com.rpl.portal_ta.data.TA;
import com.rpl.portal_ta.repository.HomeRepository;

@Service
public class HomeService {
    
    @Autowired
    private HomeRepository homeRepository;
    
    public List<Semester> getSemesters(){
        return homeRepository.getSemesters();
    }
    public List<TA> getTA(){
        return homeRepository.getTA();
    }
    public TA getTA(Integer id_ta){
        return homeRepository.getTA(id_ta);
    }
    public List<Sidang> getSidang(){
        return homeRepository.getSidang();
    }
    public Semester getSemesterById(int semesterId) {
        return getSemesters().stream()
            .filter(semester -> semester.getSemesterId() == semesterId)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Semester not found"));
    }
}
