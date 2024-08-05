package com.example.capstone.Service;

import com.example.capstone.Model.Category;
import com.example.capstone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final UserService userService;
    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public Category addCategory(Category category) {
        categories.add(category);
        return category;
    }

    public boolean updateCategory(int id, Category category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                categories.get(i).setId(category.getId());
                categories.get(i).setName(category.getName());
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(int id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

    //endpoint to check if the role is admin he can add category
    public boolean adminAddCategory(int userId, Category category) {
        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }
        if (user.getRole().equals("Admin")) {
            categories.add(category);
            return true;
        }
        return false;
    }

    public boolean adminRemoveCategory(int userId, int id) {
        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }
        if (user.getRole().equals("Admin")) {
            for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).getId() == id) {
                    categories.remove(i);
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
