package rtuitlab.products.services;

import rtuitlab.products.dto.product.GetProductDTO;
import rtuitlab.products.dto.product.PostPutProductDTO;

import java.util.List;

public interface ProductService {
    List<GetProductDTO> getAll();
    GetProductDTO getById(int id);
    List<GetProductDTO> create(PostPutProductDTO postProductDTO);
    GetProductDTO update(int id, PostPutProductDTO putProductDTO);
    List<GetProductDTO> deleteById(int id);
    List<GetProductDTO> deleteAll();
}
