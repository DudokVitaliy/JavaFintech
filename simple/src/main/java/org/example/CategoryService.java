package org.example;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private List<Category> categories = new ArrayList<>();
    private int idCounter = 1;

    public void addCategory(String name) {
        categories.add(new Category(idCounter++, name));
    }

    public void getAll() {
        if (categories.isEmpty()) {
            System.out.println("No categories");
            return;
        }

        for (Category c : categories) {
            System.out.println(c.getId() + " - " + c.getName());
        }
    }

    public void update(int id, String newName) {
        for (Category c : categories) {
            if (c.getId() == id) {
                c.setName(newName);
                return;
            }
        }
        System.out.println("Category not found");
    }

    public void delete(int id) {
        categories.removeIf(c -> c.getId() == id);
    }
}