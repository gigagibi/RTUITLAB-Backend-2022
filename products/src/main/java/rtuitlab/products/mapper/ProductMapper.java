package rtuitlab.products.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import rtuitlab.products.dto.product.*;
import rtuitlab.products.dto.productRabbit.ProductToDeliveriesDTO;
import rtuitlab.products.entities.ProductEntity;

@Component
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper extends CommonMapper<ProductEntity, ProductGetDTO, ProductPostDTO, ProductPutDTO, ProductPostedDTO, ProductUpdatedDTO> {
    @Override
    @Mapping(source = "productEntity.categoryEntity", target = "category")
    ProductGetDTO entityToGetDTO(ProductEntity productEntity);

    @Override
    @Mapping(source = "productEntity.categoryEntity", target = "category")
    ProductPostedDTO entityToPostedDTO(ProductEntity productEntity);

    @Override
    @Mapping(source = "productEntity.categoryEntity", target = "category")
    ProductUpdatedDTO entityToUpdatedDTO(ProductEntity productEntity);

    @Mapping(source = "productEntity.categoryEntity", target = "category")
    ProductToDeliveriesDTO entityToDeliveriesDTO(ProductEntity productEntity);
}
