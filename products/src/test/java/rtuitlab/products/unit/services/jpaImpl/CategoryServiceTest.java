package rtuitlab.products.unit.services.jpaImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rtuitlab.products.dto.category.*;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.mapper.CategoryMapper;
import rtuitlab.products.repositories.CategoryRepository;
import rtuitlab.products.services.CategoryService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryServiceTest extends AbstractServiceTest<CategoryEntity, CategoryGetDTO, CategoryPostDTO, CategoryPutDTO, CategoryPostedDTO, CategoryUpdatedDTO, CategoryService, CategoryMapper, CategoryRepository>{
    public CategoryServiceTest() {
        CategoryMapper mockCategoryMapper = Mockito.mock(CategoryMapper.class);
        CategoryRepository mockCategoryRepository = Mockito.mock(CategoryRepository.class);
        this.service = new CategoryService(mockCategoryRepository, mockCategoryMapper);
        this.mockRepository = mockCategoryRepository;
        this.mockMapper = mockCategoryMapper;
        this.eSupplier = () -> new CategoryEntity(
                1,
                "name"
        );
        this.getSupplier = () -> new CategoryGetDTO(
                1,
                "name"
        );
        this.postSupplier = () -> new CategoryPostDTO(
                "name"
        );
        this.putSupplier = () -> new CategoryPutDTO(
                "name"
        );
        this.eArgumentCaptorSupplier = () -> ArgumentCaptor.forClass(CategoryEntity.class);
    }
}