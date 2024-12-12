package com.rpl.portal_ta.data;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mahasiswa {
    
    @Size(min = 10, max = 10)
    private String npm;

    @NotNull
    @Size(min = 3, max = 50)
    private String nama;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 50)
    private String password;

}
