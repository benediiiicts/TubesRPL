package com.rpl.portal_ta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpl.portal_ta.data.Semester;
import com.rpl.portal_ta.repository.SemesterRepository;

@Service
public class SemesterService {
    
    @Autowired
    private SemesterRepository semesterRepository;


    public Semester getSemester(){
        return semesterRepository.getSemester();
    }

    public void addSemester(String tahunAjaran, String periode){
        Semester semester = new Semester();
        semester.setTahunAjaran(tahunAjaran);
        semester.setPeriode(periode);
        semesterRepository.addSemester(semester);
    }
}
