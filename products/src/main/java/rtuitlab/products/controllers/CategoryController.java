package rtuitlab.products.controllers;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import rtuitlab.products.dto.category.*;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.services.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
@Api("Controller for categories")
public class CategoryController extends AbstractController<CategoryEntity, CategoryGetDTO, CategoryPostDTO, CategoryPutDTO, CategoryPostedDTO, CategoryUpdatedDTO, CategoryService> {
    public CategoryController(CategoryService service) {
        super(service);
    }
}
