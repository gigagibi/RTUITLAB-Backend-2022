package rtuitlab.products.unit.mapper.notTest;

import org.springframework.beans.factory.annotation.Autowired;
import rtuitlab.products.dto.product.*;
import rtuitlab.products.dto.productRabbit.ProductToDeliveriesDTO;
import rtuitlab.products.entities.ProductEntity;
import rtuitlab.products.mapper.CategoryMapper;
import rtuitlab.products.mapper.CategoryMapperImpl;
import rtuitlab.products.mapper.ProductMapper;

public class CustomProductMapperImpl implements ProductMapper {
    private final CategoryMapper categoryMapper;

    public CustomProductMapperImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ProductEntity postDTOToEntity(ProductPostDTO post) {
        if ( post == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName( post.getName() );
        productEntity.setCost( post.getCost() );
        productEntity.setDescription( post.getDescription() );
        productEntity.setImagePath( post.getImagePath() );

        return productEntity;
    }

    @Override
    public ProductEntity putDTOToEntity(ProductPutDTO put) {
        if ( put == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName( put.getName() );
        productEntity.setCost( put.getCost() );
        productEntity.setDescription( put.getDescription() );
        productEntity.setImagePath( put.getImagePath() );

        return productEntity;
    }

    @Override
    public ProductGetDTO entityToGetDTO(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductGetDTO productGetDTO = new ProductGetDTO();

        productGetDTO.setCategory( categoryMapper.entityToGetDTO( productEntity.getCategoryEntity() ) );
        productGetDTO.setId( productEntity.getId() );
        productGetDTO.setName( productEntity.getName() );
        productGetDTO.setCost( productEntity.getCost() );
        productGetDTO.setDescription( productEntity.getDescription() );

        return productGetDTO;
    }

    @Override
    public ProductPostedDTO entityToPostedDTO(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductPostedDTO productPostedDTO = new ProductPostedDTO();

        productPostedDTO.setCategory( categoryMapper.entityToGetDTO( productEntity.getCategoryEntity() ) );
        productPostedDTO.setId( productEntity.getId() );
        productPostedDTO.setName( productEntity.getName() );
        productPostedDTO.setCost( productEntity.getCost() );
        productPostedDTO.setDescription( productEntity.getDescription() );
        productPostedDTO.setImagePath( productEntity.getImagePath() );

        return productPostedDTO;
    }

    @Override
    public ProductUpdatedDTO entityToUpdatedDTO(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductUpdatedDTO productUpdatedDTO = new ProductUpdatedDTO();

        productUpdatedDTO.setCategory( categoryMapper.entityToGetDTO( productEntity.getCategoryEntity() ) );
        productUpdatedDTO.setId( productEntity.getId() );
        productUpdatedDTO.setName( productEntity.getName() );
        productUpdatedDTO.setCost( productEntity.getCost() );
        productUpdatedDTO.setDescription( productEntity.getDescription() );
        productUpdatedDTO.setImagePath( productEntity.getImagePath() );

        return productUpdatedDTO;
    }

    @Override
    public ProductToDeliveriesDTO entityToDeliveriesDTO(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductToDeliveriesDTO productToDeliveriesDTO = new ProductToDeliveriesDTO();

        productToDeliveriesDTO.setCategory( categoryMapper.entityToDeliveriesDTO( productEntity.getCategoryEntity() ) );
        productToDeliveriesDTO.setId( productEntity.getId() );
        productToDeliveriesDTO.setName( productEntity.getName() );
        productToDeliveriesDTO.setCost( productEntity.getCost() );
        productToDeliveriesDTO.setDescription( productEntity.getDescription() );

        return productToDeliveriesDTO;
    }
}
