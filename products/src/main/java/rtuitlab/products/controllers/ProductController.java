package rtuitlab.products.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.products.dto.product.GetProductDTO;
import rtuitlab.products.dto.product.PostPutProductDTO;
import rtuitlab.products.dto.product.PostedPutProductDTO;
import rtuitlab.products.exception.category.CategoryNotFoundException;
import rtuitlab.products.exception.product.ProductNotFoundException;
import rtuitlab.products.exception.product.ProductWithGivenCategoryNotFoundException;
import rtuitlab.products.exception.product.ProductWithGivenNameNotFoundException;
import rtuitlab.products.services.ProductService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("/")
    @ApiOperation(value = "get all products", produces = "application/json")
    private List<GetProductDTO> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get product with given id", produces = "application/json")
    private GetProductDTO getProduct(@PathVariable Integer id) {
        try {
            return productService.getById(id);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/image")
    @ApiOperation(value = "get image of the products as byte array", produces = "application/octet-stream")
    private byte[] getProductImage(@PathVariable int id) {
        try {
            return productService.getImageByProductId(id);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/withCategory/{id}")
    @ApiOperation(value = "get all products with category that has given id", produces = "application/json")
    private List<GetProductDTO> getProductsByCategoryId(@PathVariable int id) {
        try {
            return productService.getByCategoryId(id);
        } catch (CategoryNotFoundException | ProductWithGivenCategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/withName/{name}")
    @ApiOperation(value = "get all products with given name", produces = "application/json")
    private GetProductDTO getProductsByName(@PathVariable String name) {
        try {
            return productService.getByName(name);
        } catch (ProductWithGivenNameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "add product", produces = "application/json")
    private List<PostedPutProductDTO> addProduct(@RequestBody PostPutProductDTO postProductDTO) {
        return productService.create(postProductDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update product with given id", produces = "application/json")
    private PostedPutProductDTO updateProduct(@PathVariable int id, @RequestBody PostPutProductDTO putProductDTO) {
        try {
            return productService.update(id, putProductDTO);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete product with given id", produces = "application/json")
    private List<GetProductDTO> deleteProduct(@PathVariable int id) {
        try {
            return productService.deleteById(id);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "delete all categories", produces = "application/json")
    private List<GetProductDTO> deleteProducts() {
        return productService.deleteAll();
    }


}
