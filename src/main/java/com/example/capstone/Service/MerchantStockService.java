package com.example.capstone.Service;

import com.example.capstone.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStockList = new ArrayList<MerchantStock>();
    public ArrayList<MerchantStock> getMerchantStockList() {
        return merchantStockList;
    }
    public MerchantStock addMerchantStock(MerchantStock merchantStock) {
        merchantStockList.add(merchantStock);
        return merchantStock;
    }
    public  boolean updateMerchantStock(int id,MerchantStock merchantStock) {
        for (int i = 0; i < merchantStockList.size(); i++) {
            if (merchantStockList.get(i).getId() == id) {
                merchantStockList.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }
    public boolean deleteMerchantStock(int id) {
        for (int i = 0; i < merchantStockList.size(); i++) {
            if (merchantStockList.get(i).getId() == id) {
                merchantStockList.remove(i);
                return true;
            }
        }
        return false;
    }
    public MerchantStock getMerchantStock(int id) {
        for (int i = 0; i < merchantStockList.size(); i++) {
            if (merchantStockList.get(i).getId() == id) {
                return merchantStockList.get(i);
            }
        }
        return null;
    }

    //user can add more stocks of product to a merchant
    //Stock
    public boolean changeMerchantStock(int merchantId, int productId, int amount, MerchantStock merchantStock) {
        for (MerchantStock stock : merchantStockList) {
            if (stock.getMerchantId() == merchantId && stock.getProductId() == productId) {
                stock.setStock(stock.getStock() + amount);
                return true;
            }
        }
        return false;
    }
}
