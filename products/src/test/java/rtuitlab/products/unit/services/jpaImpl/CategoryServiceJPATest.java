package rtuitlab.products.unit.services.jpaImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rtuitlab.products.dto.category.PostPutCategoryDTO;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.exception.category.CategoryNotFoundException;
import rtuitlab.products.mapper.CategoryMapper;
import rtuitlab.products.repositories.CategoryRepository;
import rtuitlab.products.services.jpaImpl.CategoryServiceJPA;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceJPATest {
    private CategoryServiceJPA underTest;
    @Mock private CategoryRepository categoryRepository;
    @Mock private CategoryMapper categoryMapper;

    private CategoryEntity testCategoryEntity;
    private PostPutCategoryDTO testPostPutCategoryDTO;

    @BeforeEach
    void setUp() {
        underTest = new CategoryServiceJPA(categoryRepository, categoryMapper);
        testCategoryEntity = new CategoryEntity(1, "category");
        testPostPutCategoryDTO = new PostPutCategoryDTO("category");
    }

    @Test
    void shouldGetAllCategories() {
        // arrange (no arrange given)

        // act
        underTest.getAll();
        // assert
        verify(categoryRepository).findAll();
    }

    @Test
    void shouldGetCategoryById() throws CategoryNotFoundException {
        // arrange
        Integer id = testCategoryEntity.getId();
        given(categoryRepository.findById(id))
                .willReturn(Optional.of(testCategoryEntity));

        // act
        underTest.getById(id);

        // assert
        ArgumentCaptor<Integer> idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(categoryRepository).findById(idArgumentCaptor.capture());
        Integer capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void shouldCreateCategory() {
        // arrange
        given(categoryMapper.postPutDTOToEntity(testPostPutCategoryDTO))
                .willReturn(testCategoryEntity);

        // act
        underTest.create(testPostPutCategoryDTO);

        // assert
        ArgumentCaptor<CategoryEntity> categoryEntityArgumentCaptor = ArgumentCaptor.forClass(CategoryEntity.class);
        verify(categoryRepository).save(categoryEntityArgumentCaptor.capture());
        CategoryEntity capturedCategoryEntity = categoryEntityArgumentCaptor.getValue();
        assertThat(capturedCategoryEntity.getId()).isEqualTo(testCategoryEntity.getId());
        assertThat(capturedCategoryEntity.getName()).isEqualTo(testPostPutCategoryDTO.getName());
    }

    @Test
    void shouldUpdateCategoryById() throws CategoryNotFoundException {
        // arrange
        Integer id = testCategoryEntity.getId();
        given(categoryMapper.postPutDTOToEntity(testPostPutCategoryDTO))
                .willReturn(testCategoryEntity);
        given(categoryRepository.findById(id))
                .willReturn(Optional.of(testCategoryEntity));

        // act
        underTest.update(id, testPostPutCategoryDTO);

        // assert
        ArgumentCaptor<CategoryEntity> categoryEntityArgumentCaptor = ArgumentCaptor.forClass(CategoryEntity.class);
        verify(categoryRepository).save(categoryEntityArgumentCaptor.capture());
        CategoryEntity capturedCategoryEntity = categoryEntityArgumentCaptor.getValue();
        assertThat(capturedCategoryEntity.getId()).isEqualTo(testCategoryEntity.getId());
        assertThat(capturedCategoryEntity.getName()).isEqualTo(testPostPutCategoryDTO.getName());
    }

    @Test
    void shouldDeleteCategoryById() throws CategoryNotFoundException {
        // arrange
        Integer id = testCategoryEntity.getId();
        given(categoryRepository.findById(id))
                .willReturn(Optional.of(testCategoryEntity));

        // act
        underTest.deleteById(id);

        // assert
        ArgumentCaptor<Integer> idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(categoryRepository).deleteById(idArgumentCaptor.capture());
        Integer capturedId = idArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void shouldDeleteAllCategories() {
        // arrange (no given arrange)

        // act
        underTest.deleteAll();

        // assert
        verify(categoryRepository).deleteAll();
    }

    @Test
    void shouldThrow_CategoryNotFoundException_When_GettingCategoryById() {
        // arrange
        Integer categoryId = testCategoryEntity.getId();

        // act
        // assert
        assertThatThrownBy(() -> underTest.getById(categoryId)).hasMessageContaining("Category with id = " + categoryId + " is not found");
    }
}