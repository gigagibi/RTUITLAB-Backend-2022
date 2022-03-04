package rtuitlab.products.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.products.dto.category.GetCategoryDTO;
import rtuitlab.products.dto.category.PostPutCategoryDTO;
import rtuitlab.products.dto.category.SetCategoryDTO;
import rtuitlab.products.entities.CategoryEntity;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryEntity postPutDTOToEntity(PostPutCategoryDTO postPutCategoryDTO);
    GetCategoryDTO entityToDTO(CategoryEntity categoryEntity);
    CategoryEntity setDTOToEntity(SetCategoryDTO setCategoryDTO);
    SetCategoryDTO entityToSetDTO(CategoryEntity categoryEntity);
}
