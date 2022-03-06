package rtuitlab.products.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rtuitlab.products.dto.category.GetCategoryDTO;
import rtuitlab.products.dto.category.PostPutCategoryDTO;
import rtuitlab.products.dto.category.SetCategoryDTO;
import rtuitlab.products.entities.CategoryEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CategoryMapperTest {
    private CategoryMapperImpl categoryMapper;

    private CategoryEntity testCategoryEntity;
    private PostPutCategoryDTO testPostPutCategoryDTO;
    private SetCategoryDTO testSetCategoryDTO;

    @BeforeEach
    void setUp() {
        categoryMapper = new CategoryMapperImpl();
        testCategoryEntity = new CategoryEntity(1, "category");
        testPostPutCategoryDTO = new PostPutCategoryDTO("name");
        testSetCategoryDTO = new SetCategoryDTO(1);
    }

    @Test
    void shouldConvertPostPutDTOToEntity() {
        // arrange (no given arrange)

        // act
        CategoryEntity categoryEntity = categoryMapper.postPutDTOToEntity(testPostPutCategoryDTO);

        // assert
        assertThat(categoryEntity.getName()).isEqualTo(testPostPutCategoryDTO.getName());
    }

    @Test
    void shouldConvertEntityToDTO() {
        // arrange (no given arrange)

        // act
        GetCategoryDTO getCategoryDTO = categoryMapper.entityToDTO(testCategoryEntity);

        // assert
        assertThat(getCategoryDTO.getName()).isEqualTo(testCategoryEntity.getName());
        assertThat(getCategoryDTO.getId()).isEqualTo(testCategoryEntity.getId());
    }

    @Test
    void shouldConvertSetDTOToEntity() {
        // arrange (no given arrange)

        // act
        CategoryEntity categoryEntity = categoryMapper.setDTOToEntity(testSetCategoryDTO);

        // assert
        assertThat(categoryEntity.getId()).isEqualTo(testSetCategoryDTO.getId());
    }

    @Test
    void entityToSetDTO() {
        // arrange (no given arrange)

        // act
        SetCategoryDTO setCategoryDTO = categoryMapper.entityToSetDTO(testCategoryEntity);

        // assert
        assertThat(setCategoryDTO.getId()).isEqualTo(testCategoryEntity.getId());
        assertThat(setCategoryDTO.getId()).isEqualTo(testCategoryEntity.getId());
    }
}