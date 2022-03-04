package rtuitlab.products.services;

import rtuitlab.products.dto.category.GetCategoryDTO;
import rtuitlab.products.dto.category.PostPutCategoryDTO;

import java.util.List;

public interface CategoryService {
    List<GetCategoryDTO> getAll();
    GetCategoryDTO getById(int id);
    List<GetCategoryDTO> create(PostPutCategoryDTO postCategoryDTO);
    GetCategoryDTO update(int id, PostPutCategoryDTO putCategoryDTO);
    List<GetCategoryDTO> deleteById(int id);
    List<GetCategoryDTO> deleteAll();
}
