package com.example.capstone.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotNull(message = "ID cannot be empty")
    private int id;
    @NotNull(message = "productId cannot be empty")
    private int productId;
    @NotNull(message = "merchantId cannot be empty")
    private int merchantId;
    @Min(value = 10, message = " stock have to be more than 10 at start)")
    private int stock;
}
