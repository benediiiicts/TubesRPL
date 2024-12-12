package com.rpl.portal_ta.data;

import java.sql.Date;
import java.sql.Time;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sidang{
    @NotNull
    private int sidangId;

    @NotNull
    private int semesterId;

    @NotNull
    private int taId;

    @NotNull
    @Size(min = 10, max = 10)
    private String nikPenguji1;
    
    @NotNull
    @Size(min = 10, max = 10)
    private String nikPenguji2;

    @NotNull
    private Date tanggal;

    @NotNull
    private Time waktu;

    @NotNull
    private String tempat;
}