package rtuitlab.products.services.jpaImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.products.dto.category.GetCategoryDTO;
import rtuitlab.products.dto.category.PostPutCategoryDTO;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.exception.category.CategoryNotFoundException;
import rtuitlab.products.mapper.CategoryMapper;
import rtuitlab.products.repositories.CategoryRepository;
import rtuitlab.products.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceJPA implements CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    @Override
    public List<GetCategoryDTO> getAll() {
        return categoryRepository.findAll().stream().map(c -> categoryMapper.entityToDTO(c)).collect(Collectors.toList());
    }

    @Override
    public GetCategoryDTO getById(int id) throws CategoryNotFoundException {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.entityToDTO(categoryEntity);
    }

    @Override
    public List<GetCategoryDTO> create(PostPutCategoryDTO postCategoryDTO) {
        categoryRepository.save(categoryMapper.postPutDTOToEntity(postCategoryDTO));
        return categoryRepository.findAll().stream().map(c -> categoryMapper.entityToDTO(c)).collect(Collectors.toList());
    }

    @Override
    public GetCategoryDTO update(int id, PostPutCategoryDTO putCategoryDTO) throws CategoryNotFoundException {
        if(categoryRepository.findById(id).isEmpty())
            throw new CategoryNotFoundException(id);
        CategoryEntity categoryEntity = categoryMapper.postPutDTOToEntity(putCategoryDTO);
        categoryEntity.setId(id);
        categoryRepository.save(categoryEntity);
        return categoryMapper.entityToDTO(categoryRepository.getById(id));
    }

    @Override
    public List<GetCategoryDTO> deleteById(int id) throws CategoryNotFoundException {
        if(categoryRepository.findById(id).isEmpty())
            throw new CategoryNotFoundException(id);
        categoryRepository.deleteById(id);
        return categoryRepository.findAll().stream().map(c -> categoryMapper.entityToDTO(c)).collect(Collectors.toList());
    }

    @Override
    public List<GetCategoryDTO> deleteAll() {
        categoryRepository.deleteAll();
        return categoryRepository.findAll().stream().map(c -> categoryMapper.entityToDTO(c)).collect(Collectors.toList());
    }
}
