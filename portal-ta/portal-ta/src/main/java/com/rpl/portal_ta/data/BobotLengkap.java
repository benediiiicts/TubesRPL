package com.rpl.portal_ta.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BobotLengkap {
    @NotNull
    private String topic;
    @NotNull
    private int semesterId;
    @NotNull
    private int roleId;
    @NotNull
    private int komponenId;
    @NotNull
    @Size(min = 3, max = 50)
    private String komponen;
    @NotNull
    private double persentase;
    @NotNull
    private int nilai;
    @NotNull
    private int nilai_final;
}
