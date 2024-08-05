package com.example.capstone.Service;

import com.example.capstone.Model.MerchantStock;
import com.example.capstone.Model.Product;
import com.example.capstone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final UserService userService;
    private final MerchantStockService merchantStockService;


    ArrayList<Product> products = new ArrayList<>();


    public ArrayList<Product> getProducts() {
        return products;
    }


    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public boolean updateProduct(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }
public Product getProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return products.get(i);

            }

        }
        return null;
}

    public boolean buyProduct(int userId, int productId, int merchantId) {
        User user = userService.getUser(userId);
        MerchantStock merchantStock = merchantStockService.getMerchantStock(merchantId);
        Product product = null;

        for (Product p : products) {
            if (p.getId() == productId) {
                product = p;
                break;
            }
        }

        if (user == null || merchantStock == null || product == null) {
            return false;
        }
        if (user.getRole().equals("Customer")) {
            if (merchantStock.getStock() < 1) {
                return false;
            }

            if (user.getBalance() < product.getPrice()) {
                return false;
            }
            merchantStock.setStock(merchantStock.getStock() - 1);
            user.setBalance(user.getBalance() - product.getPrice());
            return true;
        }
        return false;
    }

    public Map<String, Product> applyDiscount(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                if (product.getPrice() > 200) {
                    Product originalProduct = new Product(product);
                    double discountedPrice = product.getPrice() * 0.90;
                    product.setPrice(discountedPrice);
                    Map<String, Product> response = new HashMap<>();
                    response.put("beforeDiscount", originalProduct);
                    response.put("afterDiscount", product);
                    return response;
                }
            }
        }
        return null;
    }
    //search
    public List<Product> searchProductsByName(String name) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : getProducts()) {
            if (name.equalsIgnoreCase("all")) {
                return products;

            }
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }




}
