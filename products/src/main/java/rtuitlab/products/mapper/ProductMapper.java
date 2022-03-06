package rtuitlab.products.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import rtuitlab.products.dto.product.GetProductDTO;
import rtuitlab.products.dto.product.PostPutProductDTO;
import rtuitlab.products.dto.product.PostedPutProductDTO;
import rtuitlab.products.entities.ProductEntity;

@Component
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mapping(source = "postPutProductDTO.category", target = "categoryEntity")
    ProductEntity postPutDTOToEntity(PostPutProductDTO postPutProductDTO);

    @Mapping(source = "productEntity.categoryEntity", target = "category")
    GetProductDTO entityToDTO(ProductEntity productEntity);

    @Mapping(source = "productEntity.categoryEntity", target = "category")
    PostedPutProductDTO entityToSavedDTO(ProductEntity productEntity);
}
