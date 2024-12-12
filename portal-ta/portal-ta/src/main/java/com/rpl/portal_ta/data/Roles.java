package com.rpl.portal_ta.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Roles{
    
    @NotNull
    private int roleId;

    @NotNull
    @Size(min = 3, max = 50)
    private String roleName;
}