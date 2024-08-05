package com.example.capstone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotNull(message = "ID cannot be empty")
    private int id;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name size must be between 3 - 20")
    private String name;
}
