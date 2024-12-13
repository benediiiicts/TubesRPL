package com.rpl.portal_ta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpl.portal_ta.data.Dosen;
import com.rpl.portal_ta.data.Mahasiswa;
import com.rpl.portal_ta.repository.LoginRepository;

@Service
public class LoginService {
    
    @Autowired
    LoginRepository loginRepository;
    
    // public String findUser(String email){
    //     try {
    //         return loginRepository.getUser(email);
    //     } catch (Exception e) {
    //         return null;
    //     }
    // }
    public Object findUser(String email){
        return loginRepository.findUser(email);
    }

    
}
