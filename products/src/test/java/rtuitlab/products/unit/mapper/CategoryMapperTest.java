package rtuitlab.products.unit.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import rtuitlab.products.dto.category.*;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.mapper.CategoryMapper;
import rtuitlab.products.mapper.CategoryMapperImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CategoryMapperTest extends AbstractMapperTest<CategoryEntity, CategoryGetDTO, CategoryPostDTO, CategoryPutDTO, CategoryPostedDTO, CategoryUpdatedDTO, CategoryMapper> {

    public CategoryMapperTest() {
        this.mapper = new CategoryMapperImpl();
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
        this.postedSupplier = () -> new CategoryPostedDTO(
                1,
                "name"
        );
        this.updatedSupplier = () -> new CategoryUpdatedDTO(
                1,
                "name"
        );
    }
}