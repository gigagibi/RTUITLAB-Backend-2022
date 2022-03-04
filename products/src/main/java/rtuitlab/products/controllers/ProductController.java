package rtuitlab.products.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.products.dto.product.GetProductDTO;
import rtuitlab.products.dto.product.PostPutProductDTO;
import rtuitlab.products.dto.product.PostedPutProductDTO;
import rtuitlab.products.exception.ProductNotFoundException;
import rtuitlab.products.services.ProductService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("/")
    private List<GetProductDTO> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    private GetProductDTO getProduct(@PathVariable Integer id) {
        try {
            return productService.getById(id);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/image")
    private byte[] getProductImage(@PathVariable int id) {
        try {
            BufferedImage bImage = ImageIO.read(new File(productService.getImagePath(id)));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos );
            return bos.toByteArray();
        } catch (ProductNotFoundException | IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/")
    private List<PostedPutProductDTO> addProduct(@RequestBody PostPutProductDTO postProductDTO) {
        return productService.create(postProductDTO);
    }

    @PutMapping("/{id}")
    private PostedPutProductDTO updateProduct(@PathVariable int id, @RequestBody PostPutProductDTO putProductDTO) {
        try {
            return productService.update(id, putProductDTO);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    private List<GetProductDTO> deleteProduct(@PathVariable int id) {
        try {
            return productService.deleteById(id);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/")
    private List<GetProductDTO> deleteProducts() {
        return productService.deleteAll();
    }


}
