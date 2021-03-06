package rtuitlab.products.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rtuitlab.products.dto.category.*;
import rtuitlab.products.dto.categoryRabbit.CategoryToDeliveriesDTO;
import rtuitlab.products.entities.CategoryEntity;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper extends CommonMapper<CategoryEntity, CategoryGetDTO, CategoryPostDTO, CategoryPutDTO, CategoryPostedDTO, CategoryUpdatedDTO> {
    CategoryToDeliveriesDTO entityToDeliveriesDTO(CategoryEntity categoryEntity);
}
