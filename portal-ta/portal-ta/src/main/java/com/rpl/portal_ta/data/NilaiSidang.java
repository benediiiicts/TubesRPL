package com.rpl.portal_ta.data;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NilaiSidang{
    @NotNull
    private int sidangId;
    @NotNull
    @Size(min = 10, max = 10)
    private String npm;

    @NotNull
    @Size(min = 10, max = 10)
    private String nikDosen;

    private double nilai;

    @Size(min = 0, max = 500)
    private String komentar;
}