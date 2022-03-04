package rtuitlab.products.services;

import rtuitlab.products.dto.product.GetProductDTO;
import rtuitlab.products.dto.product.PostPutProductDTO;
import rtuitlab.products.dto.product.PostedPutProductDTO;
import rtuitlab.products.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<GetProductDTO> getAll();
    GetProductDTO getById(int id) throws ProductNotFoundException;
    List<PostedPutProductDTO> create(PostPutProductDTO postProductDTO);
    PostedPutProductDTO update(int id, PostPutProductDTO putProductDTO) throws ProductNotFoundException;
    List<GetProductDTO> deleteById(int id) throws ProductNotFoundException;
    List<GetProductDTO> deleteAll();
    String getImagePath(int id) throws ProductNotFoundException;
}
