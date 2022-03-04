package rtuitlab.products.services;

import rtuitlab.products.dto.category.GetCategoryDTO;
import rtuitlab.products.dto.category.PostPutCategoryDTO;
import rtuitlab.products.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    List<GetCategoryDTO> getAll();
    GetCategoryDTO getById(int id) throws CategoryNotFoundException;
    List<GetCategoryDTO> create(PostPutCategoryDTO postCategoryDTO);
    GetCategoryDTO update(int id, PostPutCategoryDTO putCategoryDTO) throws CategoryNotFoundException;
    List<GetCategoryDTO> deleteById(int id) throws CategoryNotFoundException;
    List<GetCategoryDTO> deleteAll();
}
