package com.example.capstone.Service;

import com.example.capstone.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
    ArrayList<Merchant> merchants = new ArrayList<Merchant>();

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }
    public Merchant addMerchant(Merchant merchant) {
        merchants.add(merchant);
        return merchant;
    }
    public boolean updateMerchant(int id,Merchant merchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.set(i, merchant);
                return true;
            }
        }
        return false;

    }
    public boolean deleteMerchant(int id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }
}
