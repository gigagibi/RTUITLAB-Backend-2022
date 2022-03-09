package rtuitlab.products.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.products.dto.category.GetCategoryDTO;
import rtuitlab.products.dto.category.PostPutCategoryDTO;
import rtuitlab.products.exception.category.CategoryNotFoundException;
import rtuitlab.products.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@Api("Controller for categories")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping(value = "/")
    @ApiOperation(value = "get all categories", produces = "application/json")
    private List<GetCategoryDTO> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get category with given id", produces = "application/json")
    private GetCategoryDTO getCategory(@PathVariable Integer id) {
        try {
            return categoryService.getById(id);
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "add category", produces = "application/json")
    private List<GetCategoryDTO> addCategory(@RequestBody PostPutCategoryDTO postCategoryDTO) {
        return categoryService.create(postCategoryDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update category with given id", produces = "application/json")
    private GetCategoryDTO updateCategory(@PathVariable int id, @RequestBody PostPutCategoryDTO putCategoryDTO) {
        try {
            return categoryService.update(id, putCategoryDTO);
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete category with given id", produces = "application/json")
    private List<GetCategoryDTO> deleteCategory(@PathVariable int id) {
        try {
            return categoryService.deleteById(id);
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "delete all categories", produces = "application/json")
    private List<GetCategoryDTO> deleteCategories() {
        return categoryService.deleteAll();
    }
}
