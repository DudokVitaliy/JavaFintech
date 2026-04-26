package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.category.CategoryItemDTO;
import org.example.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public String list(Model model){
        var items = categoryService.getAll();
        model.addAttribute("categories", items);
        return "categories/list";
    }

    @GetMapping("/add")
    public String createForm(Model model){
        model.addAttribute("category", new CategoryItemDTO());
        return "categories/category_create";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute CategoryItemDTO category){
        categoryService.save(category);
        return "redirect:/categories/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("category", categoryService.getById(id));
        return "categories/category_edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute CategoryItemDTO category){
        categoryService.save(category);
        return "redirect:/categories/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        categoryService.delete(id);
        return "redirect:/categories/list";
    }
}