package com.rpl.portal_ta.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TA{
    @NotNull
    private int taId;

    @NotNull
    private int semesterId;

    @NotNull
    @Size(min = 10, max = 10)
    private String npm;

    @NotNull
    private String nikPembimbing1;

    @NotNull
    @Size(min = 10, max = 10)
    private String nikPembimbing2;

    @NotNull
    private String topic;

    @NotNull 
    private int jenisTA;
}