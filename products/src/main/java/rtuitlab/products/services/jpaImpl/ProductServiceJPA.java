package rtuitlab.products.services.jpaImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.products.dto.product.GetProductDTO;
import rtuitlab.products.dto.product.PostPutProductDTO;
import rtuitlab.products.dto.product.PostedPutProductDTO;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.entities.ProductEntity;
import rtuitlab.products.exception.category.CategoryNotFoundException;
import rtuitlab.products.exception.product.ProductNotFoundException;
import rtuitlab.products.exception.product.ProductWithGivenCategoryNotFoundException;
import rtuitlab.products.exception.product.ProductWithGivenNameNotFoundException;
import rtuitlab.products.mapper.ProductMapper;
import rtuitlab.products.repositories.CategoryRepository;
import rtuitlab.products.repositories.ProductRepository;
import rtuitlab.products.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceJPA implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;
    @Override
    public List<GetProductDTO> getAll() {
        return productRepository.findAll().stream().map(c -> productMapper.entityToDTO(c)).collect(Collectors.toList());
    }

    @Override
    public GetProductDTO getById(int id) throws ProductNotFoundException {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.entityToDTO(productEntity);
    }

    @Override
    public List<PostedPutProductDTO> create(PostPutProductDTO postProductDTO) {
        ProductEntity productEntity = productMapper.postPutDTOToEntity(postProductDTO);
        productEntity.setCategoryEntity(categoryRepository.getById(postProductDTO.getCategory().getId()));
        productRepository.saveAndFlush(productEntity);
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(c -> productMapper.entityToSavedDTO(c)).collect(Collectors.toList());
    }

    @Override
    public PostedPutProductDTO update(int id, PostPutProductDTO putProductDTO) throws ProductNotFoundException {
        if(productRepository.findById(id).isEmpty())
            throw new ProductNotFoundException(id);
        ProductEntity productEntity = productMapper.postPutDTOToEntity(putProductDTO);
        productEntity.setId(id);
        productRepository.save(productEntity);
        return productMapper.entityToSavedDTO(productRepository.getById(id));
    }

    @Override
    public List<GetProductDTO> deleteById(int id) throws ProductNotFoundException {
        if(productRepository.findById(id).isEmpty())
            throw new ProductNotFoundException(id);
        productRepository.deleteById(id);
        return productRepository.findAll().stream().map(c -> productMapper.entityToDTO(c)).collect(Collectors.toList());
    }

    @Override
    public List<GetProductDTO> deleteAll() {
        productRepository.deleteAll();
        return productRepository.findAll().stream().map(c -> productMapper.entityToDTO(c)).collect(Collectors.toList());
    }

    @Override
    public String getImagePath(int id) throws ProductNotFoundException {
        if(productRepository.findById(id).isEmpty())
            throw new ProductNotFoundException(id);
        return productRepository.getById(id).getImagePath();
    }

    @Override
    public List<GetProductDTO> getByCategoryId(int id) throws CategoryNotFoundException, ProductWithGivenCategoryNotFoundException {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        List<ProductEntity> productEntities = productRepository.findAllByCategoryEntity(categoryEntity);
        if (productEntities.isEmpty())
            throw new ProductWithGivenCategoryNotFoundException(categoryEntity.getId());
        return productEntities.stream().map(p -> productMapper.entityToDTO(p)).collect(Collectors.toList());
    }

    @Override
    public GetProductDTO getByName(String name) throws ProductWithGivenNameNotFoundException {
        ProductEntity productEntity = productRepository.findByName(name);
        if(productEntity==null)
            throw new ProductWithGivenNameNotFoundException(name);
        return productMapper.entityToDTO(productEntity);
    }
}
