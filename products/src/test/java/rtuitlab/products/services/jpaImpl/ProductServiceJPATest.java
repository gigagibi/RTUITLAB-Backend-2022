package rtuitlab.products.services.jpaImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rtuitlab.products.dto.category.SetCategoryDTO;
import rtuitlab.products.dto.product.PostPutProductDTO;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.entities.ProductEntity;
import rtuitlab.products.exception.ProductNotFoundException;
import rtuitlab.products.mapper.ProductMapper;
import rtuitlab.products.repositories.CategoryRepository;
import rtuitlab.products.repositories.ProductRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceJPATest {

    private ProductServiceJPA underTest;

    @Mock private ProductRepository productRepository;
    @Mock private ProductMapper productMapper;
    @Mock CategoryRepository categoryRepository;

    private ProductEntity testProductEntity;
    private PostPutProductDTO testPostPutProductDTO;
    private CategoryEntity categoryEntity;

    @BeforeEach
    void setUp() {
        underTest = new ProductServiceJPA(productRepository, categoryRepository, productMapper);
        categoryEntity = new CategoryEntity(1, "category");
        testProductEntity = new ProductEntity(1, "product", 100, "desc", "/path", categoryEntity);
        SetCategoryDTO setCategoryDTO = new SetCategoryDTO(1);
        testPostPutProductDTO = new PostPutProductDTO("product", 100, "desc", "/path", setCategoryDTO);
    }

    @Test
    void shouldGetAllCategories() {
        // arrange (no arrange given)

        // act
        underTest.getAll();
        // assert
        verify(productRepository).findAll();
    }

    @Test
    void shouldGetProductById() throws ProductNotFoundException {
        // arrange
        Integer id = testProductEntity.getId();
        given(productRepository.findById(id))
                .willReturn(Optional.of(testProductEntity));

        // act
        underTest.getById(id);

        // assert
        ArgumentCaptor<Integer> idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(productRepository).findById(idArgumentCaptor.capture());
        Integer capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void shouldCreateProduct() {
        // arrange
        given(productMapper.postPutDTOToEntity(testPostPutProductDTO))
                .willReturn(testProductEntity);
        given(categoryRepository.getById(testPostPutProductDTO.getCategory().getId()))
                .willReturn(categoryEntity);

        // act
        underTest.create(testPostPutProductDTO);

        // assert
        ArgumentCaptor<ProductEntity> productEntityArgumentCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).saveAndFlush(productEntityArgumentCaptor.capture());
        ProductEntity capturedProductEntity = productEntityArgumentCaptor.getValue();
        assertThat(capturedProductEntity.getId()).isEqualTo(testProductEntity.getId());
        assertThat(capturedProductEntity.getName()).isEqualTo(testPostPutProductDTO.getName());
        assertThat(capturedProductEntity.getCategoryEntity().getId()).isEqualTo(testPostPutProductDTO.getCategory().getId());
    }

    @Test
    void shouldUpdateProductById() throws ProductNotFoundException {
        // arrange
        Integer id = testProductEntity.getId();
        given(productMapper.postPutDTOToEntity(testPostPutProductDTO))
                .willReturn(testProductEntity);
        given(productRepository.findById(id))
                .willReturn(Optional.of(testProductEntity));

        // act
        underTest.update(id, testPostPutProductDTO);

        // assert
        ArgumentCaptor<ProductEntity> productEntityArgumentCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(productEntityArgumentCaptor.capture());
        ProductEntity capturedProductEntity = productEntityArgumentCaptor.getValue();
        assertThat(capturedProductEntity.getId()).isEqualTo(testProductEntity.getId());
        assertThat(capturedProductEntity.getName()).isEqualTo(testPostPutProductDTO.getName());
    }

    @Test
    void shouldDeleteProductById() throws ProductNotFoundException {
        // arrange
        Integer id = testProductEntity.getId();
        given(productRepository.findById(id))
                .willReturn(Optional.of(testProductEntity));

        // act
        underTest.deleteById(id);

        // assert
        ArgumentCaptor<Integer> idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(productRepository).deleteById(idArgumentCaptor.capture());
        Integer capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void shouldDeleteAllCategories() {
        // arrange (no given arrange)

        // act
        underTest.deleteAll();

        // assert
        verify(productRepository).deleteAll();
    }
}