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
import rtuitlab.products.exception.category.CategoryNotFoundException;
import rtuitlab.products.exception.product.ProductNotFoundException;
import rtuitlab.products.exception.product.ProductWithGivenCategoryNotFoundException;
import rtuitlab.products.exception.product.ProductWithGivenNameNotFoundException;
import rtuitlab.products.mapper.ProductMapper;
import rtuitlab.products.repositories.CategoryRepository;
import rtuitlab.products.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.filter;
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
    private CategoryEntity testCategoryEntity;

    @BeforeEach
    void setUp() {
        underTest = new ProductServiceJPA(productRepository, categoryRepository, productMapper);
        testCategoryEntity = new CategoryEntity(1, "category");
        testProductEntity = new ProductEntity(1, "product", 100, "desc", "/path", testCategoryEntity);
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
                .willReturn(testCategoryEntity);

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
    void shouldDeleteAllProducts() {
        // arrange (no given arrange)

        // act
        underTest.deleteAll();

        // assert
        verify(productRepository).deleteAll();
    }

    @Test
    void shouldGetProductByCategoryId() throws CategoryNotFoundException, ProductWithGivenCategoryNotFoundException {
        // arrange
        Integer categoryId = testCategoryEntity.getId();
        given(categoryRepository.findById(categoryId))
                .willReturn(Optional.of(testCategoryEntity));
        given(productRepository.findAllByCategoryEntity(testCategoryEntity))
                .willReturn(List.of(testProductEntity));

        // act
        underTest.getByCategoryId(categoryId);

        // assert
        ArgumentCaptor<CategoryEntity> categoryEntityArgumentCaptor = ArgumentCaptor.forClass(CategoryEntity.class);
        verify(productRepository).findAllByCategoryEntity(categoryEntityArgumentCaptor.capture());
        CategoryEntity capturedCategoryEntity = categoryEntityArgumentCaptor.getValue();
        assertThat(capturedCategoryEntity.getId()).isEqualTo(categoryId);
    }

    @Test
    void shouldGetProductByName() throws ProductWithGivenNameNotFoundException {
        // arrange
        String name = testProductEntity.getName();
        given(productRepository.findByName(name))
                .willReturn(testProductEntity);

        // act
        underTest.getByName(name);

        // assert
        ArgumentCaptor<String> nameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(productRepository).findByName(nameArgumentCaptor.capture());
        String capturedName = nameArgumentCaptor.getValue();
        assertThat(name).isEqualTo(capturedName);
    }
}