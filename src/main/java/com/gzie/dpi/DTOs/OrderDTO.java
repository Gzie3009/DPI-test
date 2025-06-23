package com.gzie.dpi.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDTO implements Serializable {
    private Long id;

    @NotNull
    private Long customerId;

    @NotBlank
    private String productName;
}
