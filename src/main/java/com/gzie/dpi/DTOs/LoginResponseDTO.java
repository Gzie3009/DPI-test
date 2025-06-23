package com.gzie.dpi.DTOs;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private Integer status;
    private Boolean success;
    private String email;
}
