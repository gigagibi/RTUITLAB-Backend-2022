package rtuitlab.products.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.products.dto.category.GetCategoryDTO;
import rtuitlab.products.dto.category.PostPutCategoryDTO;
import rtuitlab.products.entities.ProductEntity;
import rtuitlab.products.exception.CategoryNotFoundException;
import rtuitlab.products.services.CategoryService;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping("/")
    private List<GetCategoryDTO> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    private GetCategoryDTO getCategory(@PathVariable Integer id) {
        try {
            return categoryService.getById(id);
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/")
    private List<GetCategoryDTO> addCategory(@RequestBody PostPutCategoryDTO postCategoryDTO) {
        return categoryService.create(postCategoryDTO);
    }

    @PutMapping("/{id}")
    private GetCategoryDTO updateCategory(@PathVariable int id, @RequestBody PostPutCategoryDTO putCategoryDTO) {
        try {
            return categoryService.update(id, putCategoryDTO);
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    private List<GetCategoryDTO> deleteCategory(@PathVariable int id) {
        try {
            return categoryService.deleteById(id);
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/")
    private List<GetCategoryDTO> deleteCategories() {
        return categoryService.deleteAll();
    }
}
