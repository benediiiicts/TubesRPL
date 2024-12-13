package com.rpl.portal_ta.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleDosen{
    @NotNull
    private int semesterId;

    @NotNull
    private int roleId;

    @NotNull
    @Size(min = 10, max = 10)
    private String nikDosen;
}