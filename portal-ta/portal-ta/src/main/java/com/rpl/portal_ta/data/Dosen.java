package com.rpl.portal_ta.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dosen{

    @NotNull
    @Size(min = 10, max = 10)
    private String nik;

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