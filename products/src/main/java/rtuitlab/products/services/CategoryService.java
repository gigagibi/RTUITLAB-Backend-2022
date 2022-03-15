package rtuitlab.products.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rtuitlab.products.dto.category.*;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.mapper.CategoryMapper;
import rtuitlab.products.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService extends AbstractService<CategoryEntity, CategoryRepository, CategoryGetDTO, CategoryPostDTO, CategoryPutDTO, CategoryPostedDTO, CategoryUpdatedDTO, CategoryMapper> {
    public CategoryService(CategoryRepository repository, @Qualifier("categoryMapperImpl") CategoryMapper mapper) {
        super(repository, mapper);
    }
}
