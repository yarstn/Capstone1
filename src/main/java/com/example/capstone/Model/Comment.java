
// Comment.java
package com.example.capstone.Model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;
    @NotNull(message = "userID cannot be empty")
    private int userId;
    @NotNull(message = "productID cannot be empty")
    private int productId;
    @NotNull(message = "rating cannot be empty")
    private String comment;
}

