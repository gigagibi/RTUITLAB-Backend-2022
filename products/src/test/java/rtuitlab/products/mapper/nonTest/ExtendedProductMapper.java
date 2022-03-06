package rtuitlab.products.mapper.nonTest;

import rtuitlab.products.dto.product.GetProductDTO;
import rtuitlab.products.dto.product.PostPutProductDTO;
import rtuitlab.products.dto.product.PostedPutProductDTO;
import rtuitlab.products.entities.ProductEntity;
import rtuitlab.products.mapper.CategoryMapper;
import rtuitlab.products.mapper.ProductMapper;

public class ExtendedProductMapper implements ProductMapper {
    private CategoryMapper categoryMapper;

    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ProductEntity postPutDTOToEntity(PostPutProductDTO postPutProductDTO) {
        if ( postPutProductDTO == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setCategoryEntity( categoryMapper.setDTOToEntity( postPutProductDTO.getCategory() ) );
        productEntity.setName( postPutProductDTO.getName() );
        productEntity.setCost( postPutProductDTO.getCost() );
        productEntity.setDescription( postPutProductDTO.getDescription() );
        productEntity.setImagePath( postPutProductDTO.getImagePath() );

        return productEntity;
    }

    @Override
    public GetProductDTO entityToDTO(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        GetProductDTO getProductDTO = new GetProductDTO();

        getProductDTO.setCategory( categoryMapper.entityToDTO( productEntity.getCategoryEntity() ) );
        getProductDTO.setId( productEntity.getId() );
        getProductDTO.setName( productEntity.getName() );
        getProductDTO.setCost( productEntity.getCost() );
        getProductDTO.setDescription( productEntity.getDescription() );

        return getProductDTO;
    }

    @Override
    public PostedPutProductDTO entityToSavedDTO(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        PostedPutProductDTO postedPutProductDTO = new PostedPutProductDTO();

        postedPutProductDTO.setCategory( categoryMapper.entityToDTO( productEntity.getCategoryEntity() ) );
        postedPutProductDTO.setId( productEntity.getId() );
        postedPutProductDTO.setName( productEntity.getName() );
        postedPutProductDTO.setCost( productEntity.getCost() );
        postedPutProductDTO.setDescription( productEntity.getDescription() );
        postedPutProductDTO.setImagePath( productEntity.getImagePath() );

        return postedPutProductDTO;
    }
}
