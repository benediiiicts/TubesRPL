package com.rpl.portal_ta.data;


import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Semester{
    @NotNull
    private int semesterId;
    @NotNull
    private String tahunAjaran;
    @NotNull
    private String periode;
}