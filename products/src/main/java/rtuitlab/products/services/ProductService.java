package rtuitlab.products.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rtuitlab.products.dto.product.*;
import rtuitlab.products.dto.productRabbit.ProductToDeliveriesDTO;
import rtuitlab.products.entities.CategoryEntity;
import rtuitlab.products.entities.ProductEntity;
import rtuitlab.products.exception.EntityNotFoundException;
import rtuitlab.products.mapper.ProductMapper;
import rtuitlab.products.repositories.CategoryRepository;
import rtuitlab.products.repositories.ProductRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService extends AbstractService<ProductEntity, ProductRepository, ProductGetDTO, ProductPostDTO, ProductPutDTO, ProductPostedDTO, ProductUpdatedDTO, ProductMapper> {
    private final CategoryRepository categoryRepository;
    @Autowired
    public ProductService(ProductRepository repository, @Qualifier("productMapperImpl") ProductMapper mapper, CategoryRepository categoryRepository) {
        super(repository, mapper);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductPostedDTO> create(ProductPostDTO productPostDTO) {
        ProductEntity productEntity = mapper.postDTOToEntity(productPostDTO);
        productEntity.setCategoryEntity(categoryRepository.getById(productPostDTO.getCategoryId()));
        repository.save(productEntity);
        List<ProductEntity> productEntities = repository.findAll();
        return productEntities.stream().map(mapper::entityToPostedDTO).collect(Collectors.toList());
    }

    @Override
    public ProductUpdatedDTO update(int id, ProductPutDTO productPutDTO) throws EntityNotFoundException {
        if(!repository.existsById(id))
            throw new EntityNotFoundException(id);
        ProductEntity productEntity = mapper.putDTOToEntity(productPutDTO);
        productEntity.setCategoryEntity(categoryRepository.getById(productPutDTO.getCategoryId()));
        productEntity.setId(id);
        repository.save(productEntity);
        return mapper.entityToUpdatedDTO(repository.getById(id));
    }

    public List<ProductGetDTO> getByCategoryId(int id) throws EntityNotFoundException {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        List<ProductEntity> productEntities = repository.findAllByCategoryEntity(categoryEntity);
        return productEntities.stream().map(mapper::entityToGetDTO).collect(Collectors.toList());
    }

    public ProductGetDTO getByName(String name) {
        ProductEntity productEntity = repository.findByName(name);
        return mapper.entityToGetDTO(productEntity);
    }

    public byte[] getImageByProductId(int id) throws IOException, EntityNotFoundException {
        ProductEntity productEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        String imagePath = productEntity.getImagePath();
        BufferedImage bImage = ImageIO.read(new File(imagePath));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos );
        return bos.toByteArray();
    }

    public int getProductCost(int id) throws EntityNotFoundException {
        ProductEntity productEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return productEntity.getCost();
    }

    @RabbitListener(queues = "deliveries-products-get-queue")
    public ProductToDeliveriesDTO sendProductToDeliveriesResponse(int id) throws EntityNotFoundException {
        ProductEntity productEntity = repository.findById(id).orElse(new ProductEntity(new CategoryEntity()));
        return mapper.entityToDeliveriesDTO(productEntity);
    }

    @RabbitListener(queues = "deliveries-products-get-all-queue")
    public List<ProductToDeliveriesDTO> sendAllProductsToDeliveriesResponse(String message) {
        List<ProductEntity> productEntities = repository.findAll();
        return productEntities.stream().map(mapper::entityToDeliveriesDTO).collect(Collectors.toList());
    }
}
