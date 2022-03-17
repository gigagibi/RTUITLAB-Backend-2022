package rtuitlab.products.unit.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rtuitlab.products.dto.category.CategoryGetDTO;
import rtuitlab.products.dto.product.*;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.entities.ProductEntity;
import rtuitlab.products.exception.EntityNotFoundException;
import rtuitlab.products.mapper.ProductMapper;
import rtuitlab.products.repositories.CategoryRepository;
import rtuitlab.products.repositories.ProductRepository;
import rtuitlab.products.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest extends AbstractServiceTest<ProductEntity, ProductGetDTO, ProductPostDTO, ProductPutDTO, ProductPostedDTO, ProductUpdatedDTO, ProductService, ProductMapper, ProductRepository> {
    private final CategoryRepository mockCategoryRepository;
    private final Supplier<CategoryEntity> categorySupplier;
    public ProductServiceTest() {
        ProductMapper mockProductMapper = Mockito.mock(ProductMapper.class);
        ProductRepository mockProductRepository = Mockito.mock(ProductRepository.class);
        CategoryRepository mockCategoryRepository = Mockito.mock(CategoryRepository.class);
        this.service = new ProductService(mockProductRepository, mockProductMapper, mockCategoryRepository);
        this.mockMapper = mockProductMapper;
        this.mockRepository = mockProductRepository;
        this.eSupplier = () -> new ProductEntity(
                1,
                "name",
                100,
                "desc",
                "src/test/resources/images/image_test.png",
                new CategoryEntity(1, "name")
        );
        this.getSupplier = () -> new ProductGetDTO(
                1,
                "name",
                100,
                "desc",
                new CategoryGetDTO(1, "name")
        );
        this.postSupplier = () -> new ProductPostDTO(
                "name",
                100,
                "desc",
                "src/test/resources/images/image_test.png",
                1
        );
        this.putSupplier = () -> new ProductPutDTO(
                "name",
                100,
                "desc",
                "src/test/resources/images/image_test.png",
                1
        );
        this.eArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(ProductEntity.class);
        this.categorySupplier = () -> new CategoryEntity(1, "name");
        this.mockCategoryRepository = mockCategoryRepository;
    }

    @Test
    void shouldGetProductByCategoryId() throws EntityNotFoundException {
        // arrange
        CategoryEntity categoryEntity = categorySupplier.get();
        ProductEntity productEntity = eSupplier.get();
        Integer categoryId = categoryEntity.getId();
        given(mockCategoryRepository.findById(categoryId))
                .willReturn(Optional.of(categoryEntity));
        given(mockRepository.findAllByCategoryEntity(categoryEntity))
                .willReturn(List.of(productEntity));

        // act
        service.getByCategoryId(categoryId);

        // assert
        ArgumentCaptor<CategoryEntity> categoryEntityArgumentCaptor = ArgumentCaptor.forClass(CategoryEntity.class);
        verify(mockRepository).findAllByCategoryEntity(categoryEntityArgumentCaptor.capture());
        CategoryEntity capturedCategoryEntity = categoryEntityArgumentCaptor.getValue();
        assertThat(capturedCategoryEntity.getId()).isEqualTo(categoryId);
    }

    @Test
    void shouldGetProductByName() {
        // arrange
        ProductEntity productEntity = eSupplier.get();
        String name = productEntity.getName();
        given(mockRepository.findByName(name))
                .willReturn(productEntity);

        // act
        service.getByName(name);

        // assert
        ArgumentCaptor<String> nameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockRepository).findByName(nameArgumentCaptor.capture());
        String capturedName = nameArgumentCaptor.getValue();
        assertThat(name).isEqualTo(capturedName);
    }

    @Test
    void shouldGetImageByProductId(){
        // arrange
        ProductEntity productEntity = eSupplier.get();
        Integer id = productEntity.getId();
        given(mockRepository.findById(id)).willReturn(Optional.of(productEntity));

        // act
        // assert
        assertThatCode(() -> service.getImageByProductId(id)).doesNotThrowAnyException();
    }
}