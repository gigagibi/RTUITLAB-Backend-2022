package rtuitlab.products.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.products.dto.product.*;
import rtuitlab.products.entities.ProductEntity;
import rtuitlab.products.exception.EntityNotFoundException;
import rtuitlab.products.services.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController extends AbstractController<ProductEntity, ProductGetDTO, ProductPostDTO, ProductPutDTO, ProductPostedDTO, ProductUpdatedDTO, ProductService> {
    public ProductController(ProductService service) {
        super(service);
    }

    @GetMapping("/{id}/image")
    public byte[] getProductImage(@PathVariable int id) {
        try {
            return service.getImageByProductId(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/withCategory/{id}")
    public List<ProductGetDTO> getProductsByCategoryId(@PathVariable int id) {
        try {
            return service.getByCategoryId(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with given id is not found");
        }
    }

    @GetMapping("/withName/{name}")
    public ProductGetDTO getProductsByName(@PathVariable String name) {
        return service.getByName(name);
    }
}
