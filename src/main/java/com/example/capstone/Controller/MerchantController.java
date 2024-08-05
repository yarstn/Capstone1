package com.example.capstone.Controller;

import com.example.capstone.Model.Merchant;
import com.example.capstone.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/merchant")
public class MerchantController {
    private final MerchantService merchantService;
    //GET DONE
    @GetMapping("/get")
    public ResponseEntity getMerchants()  {
        if (merchantService.getMerchants().isEmpty()) {
            return ResponseEntity.status(200).body("sorry nothing here yet..");
        }
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }
    //ADD DONE
    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        return ResponseEntity.status(200).body("Merchant added succufully "+merchantService.addMerchant(merchant));
    }
//UPDATE DONE
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant( @PathVariable int id,@Valid @RequestBody Merchant merchant) {
        boolean isUpdated = merchantService.updateMerchant(id,merchant);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Merchant updated successfully");
        }
        return ResponseEntity.status(404).body("Merchant not found");
    }
    //DELETE DONE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id) {
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body("Merchant deleted successfully");
        }
        return ResponseEntity.status(404).body("Merchant not found");
    }
}
