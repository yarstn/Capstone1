package com.example.capstone.Controller;

import com.example.capstone.Model.Product;
import com.example.capstone.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;


    //GET DONE
    @GetMapping("/get")
    public ResponseEntity getProduct() {
        if (productService.getProducts().isEmpty()) {
            return ResponseEntity.status(200).body("sorry no products here yet..");
        }
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    //ADD DONE
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        productService.addProduct(product);
        return ResponseEntity.status(201).body("Product added successfully");
    }

    //UPDATE DONE
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable int id, @RequestBody Product product) {
        boolean isUpdated = productService.updateProduct(id, product);
        if (isUpdated) {
            return ResponseEntity.status(201).body("Product updated successfully");
        }
        return ResponseEntity.status(404).body("Product not found");
    }

    //DELETE DONE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body("Product deleted successfully");
        }
        return ResponseEntity.status(404).body("Product not found");
    }
    //buy product
    @PutMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId) {
        boolean isBuy = productService.buyProduct(userId, productId, merchantId);
        if (isBuy) {
            return ResponseEntity.status(201).body("Product bought successfully");
        }
        return ResponseEntity.status(400).body("Transaction failed");
    }
    @PutMapping("/discount/{productId}")
    public ResponseEntity applyDiscount(@PathVariable int productId) {
        Map<String, Product> response = productService.applyDiscount(productId);
        if (response != null) {
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(400).body("Product not found or discount not applicable");
    }
    @GetMapping("/search/{name}")
    public ResponseEntity searchProducts(@PathVariable String name) {
        List<Product> products;
        if (name != null) {
            products = productService.searchProductsByName(name);

            if (products.isEmpty()) {
                return ResponseEntity.status(200).body("No products found matching the criteria");
            }
            return ResponseEntity.status(200).body(products);
        }
        return ResponseEntity.status(400).body("No products found matching the criteria");
    }
    //rate

}
