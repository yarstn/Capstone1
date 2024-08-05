package com.example.capstone.Controller;

import com.example.capstone.Model.MerchantStock;
import com.example.capstone.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stock")
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    //GET
    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
if(merchantStockService.getMerchantStockList().isEmpty()){
    return ResponseEntity.status(400).body("No merchant stock found");
}
return ResponseEntity.status(200).body(merchantStockService.getMerchantStockList());
    }
    //ADD
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        return ResponseEntity.status(200).body("Merchant Stock added successfully"+merchantStockService.addMerchantStock(merchantStock));
    }
    //UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable int id,@Valid @RequestBody MerchantStock merchantStock){
        boolean isUpdated = merchantStockService.updateMerchantStock(id,merchantStock);
        if(isUpdated){
            return ResponseEntity.status(200).body("Merchant Stock updated successfully");
        }
        return ResponseEntity.status(400).body("Merchant Stock update failed");
    }
    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id){
        boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("Merchant Stock deleted successfully");
        }
        return ResponseEntity.status(400).body("Merchant Stock delete failed");
    }
    @PutMapping("/change-stock/{merchantId}/{productId}/{amount}")
    public ResponseEntity changeProductStock(@PathVariable int merchantId, @PathVariable int productId, @PathVariable int amount, @RequestBody MerchantStock merchantStock) {
        boolean isChanged = merchantStockService.changeMerchantStock(merchantId, productId, amount, merchantStock);
        if (isChanged) {
            return ResponseEntity.status(200).body("Merchant Stock updated successfully");
        }
        return ResponseEntity.status(400).body("Merchant Stock update failed");
    }

}
