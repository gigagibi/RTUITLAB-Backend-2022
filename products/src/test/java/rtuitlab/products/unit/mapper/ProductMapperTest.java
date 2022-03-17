package rtuitlab.products.unit.mapper;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import rtuitlab.products.dto.category.*;
import rtuitlab.products.dto.product.*;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.entities.ProductEntity;
import rtuitlab.products.mapper.CategoryMapper;
import rtuitlab.products.mapper.CategoryMapperImpl;
import rtuitlab.products.mapper.ProductMapper;
import rtuitlab.products.mapper.ProductMapperImpl;
import rtuitlab.products.unit.mapper.notTest.CustomProductMapperImpl;

import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductMapperTest extends AbstractMapperTest<ProductEntity, ProductGetDTO, ProductPostDTO, ProductPutDTO, ProductPostedDTO, ProductUpdatedDTO, ProductMapper> {
   public ProductMapperTest() {
        this.mapper = new CustomProductMapperImpl(new CategoryMapperImpl());
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
        this.postedSupplier = () -> new ProductPostedDTO(
                1,
                "name",
                100,
                "desc",
                "src/test/resources/images/image_test.png",
                new CategoryGetDTO(1, "name")
        );
        this.updatedSupplier = () -> new ProductUpdatedDTO(
                1,
                "name",
                100,
                "desc",
                "src/test/resources/images/image_test.png",
                new CategoryGetDTO(1, "name")
        );
    }
}