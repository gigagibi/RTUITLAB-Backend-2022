package rtuitlab.products.unit.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rtuitlab.products.dto.category.SetCategoryDTO;
import rtuitlab.products.dto.product.GetProductDTO;
import rtuitlab.products.dto.product.PostPutProductDTO;
import rtuitlab.products.dto.product.PostedPutProductDTO;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.entities.ProductEntity;
import rtuitlab.products.mapper.CategoryMapperImpl;
import rtuitlab.products.unit.mapper.notTest.ExtendedProductMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductMapperTest {
    private ExtendedProductMapper productMapper;

    private ProductEntity testProductEntity;
    private PostPutProductDTO testPostPutProductDTO;

    @BeforeEach
    void setUp() {
        productMapper = new ExtendedProductMapper();
        CategoryMapperImpl categoryMapper = new CategoryMapperImpl();
        productMapper.setCategoryMapper(categoryMapper);
        CategoryEntity categoryEntity = new CategoryEntity(1, "category");
        testProductEntity = new ProductEntity(1, "product", 100, "desc", "/path", categoryEntity);
        SetCategoryDTO setCategoryDTO = new SetCategoryDTO(1);
        testPostPutProductDTO = new PostPutProductDTO("product", 100, "desc", "/path", setCategoryDTO);
    }

    @Test
    void shouldConvertPostPutDTOToEntity() {
        // arrange (no given arrange)

        // act
        ProductEntity productEntity = productMapper.postPutDTOToEntity(testPostPutProductDTO);

        // assert
        assertThat(productEntity.getName()).isEqualTo(testPostPutProductDTO.getName());
        assertThat(productEntity.getCategoryEntity().getId()).isEqualTo(testPostPutProductDTO.getCategory().getId());
    }

    @Test
    void shouldConvertEntityToDTO() {
        // arrange (no given arrange)

        // act
        GetProductDTO getProductDTO = productMapper.entityToDTO(testProductEntity);

        // assert
        assertThat(testProductEntity.getId()).isEqualTo(getProductDTO.getId());
        assertThat(testProductEntity.getCategoryEntity().getId()).isEqualTo(getProductDTO.getCategory().getId());
    }

    @Test
    void shouldConvertEntityToSavedDTO() {
        // arrange (no given arrange)

        // act
        PostedPutProductDTO savedProductDTO = productMapper.entityToSavedDTO(testProductEntity);

        // assert
        assertThat(testProductEntity.getId()).isEqualTo(savedProductDTO.getId());
        assertThat(testProductEntity.getCategoryEntity().getId()).isEqualTo(savedProductDTO.getCategory().getId());
    }
}