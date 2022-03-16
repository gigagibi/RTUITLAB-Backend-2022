package rtuitlab.orders.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rtuitlab.orders.dto.order.*;
import rtuitlab.orders.exceptions.EntityNotFoundException;
import rtuitlab.orders.mappers.OrderMapper;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.OrderDocument;
import rtuitlab.orders.repositories.OrderRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService extends AbstractService<OrderDocument, OrderRepository, OrderGetDTO, OrderPostDTO, OrderPutDTO, OrderPostedDTO, OrderUpdatedDTO, OrderMapper> {
    private final RestTemplate restTemplate;
    @Autowired
    public OrderService(OrderRepository repository, @Qualifier("orderMapperImpl") OrderMapper mapper, RestTemplate restTemplate) {
        super(repository, mapper);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<OrderPostedDTO> create(OrderPostDTO OrderPostDTO) {
        OrderDocument orderDocument = mapper.postDTOToEntity(OrderPostDTO);
        int summaryCost = 0;
        for (BoughtProductInfo boughtProductInfo: orderDocument.getProducts()) {
            int productId = boughtProductInfo.getId();
            ResponseEntity<Integer> productCostEntity = restTemplate.getForEntity(
                    "http://products/api/v1/products/" + productId + "/cost",
                    Integer.class);
            if (productCostEntity.getStatusCode()== HttpStatus.NOT_FOUND)
                summaryCost += boughtProductInfo.getCost() * boughtProductInfo.getAmount();
            else {
                boughtProductInfo.setCost(productCostEntity.getBody());
                summaryCost += productCostEntity.getBody() * boughtProductInfo.getAmount();
            }
        }
        orderDocument.setCost(summaryCost);
        orderDocument.setOrderDate(new Date());
        orderDocument.setNumber(findMaxOrderNumber() + 1);
        repository.save(orderDocument);
        return repository.findAll().stream().map(mapper::entityToPostedDTO).collect(Collectors.toList());
    }

    @Override
    public OrderUpdatedDTO update(String id, OrderPutDTO OrderPutDTO) throws EntityNotFoundException {
        if(!repository.existsById(id))
            throw new EntityNotFoundException(id);
        OrderDocument orderDocument = mapper.putDTOToEntity(OrderPutDTO);
        orderDocument.setId(id);
        int summaryCost = 0;
        for (BoughtProductInfo boughtProductInfo: orderDocument.getProducts()) {
            int productId = boughtProductInfo.getId();
            ResponseEntity<Integer> productCostEntity = restTemplate.getForEntity(
                    "http://products/api/v1/products/" + productId + "/cost",
                    Integer.class);
            if (productCostEntity.getStatusCode()==HttpStatus.NOT_FOUND)
                summaryCost += boughtProductInfo.getCost() * boughtProductInfo.getAmount();
            else {
                boughtProductInfo.setCost(productCostEntity.getBody());
                summaryCost += productCostEntity.getBody() * boughtProductInfo.getAmount();
            }
        }
        orderDocument.setCost(summaryCost);
        orderDocument.setNumber(getNumberOfOrderById(id));
        repository.save(orderDocument);
        OrderDocument updatedOrderDocument = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return mapper.entityToUpdatedDTO(updatedOrderDocument);
    }

    private int findMaxOrderNumber() {
        List<OrderDocument> orders = repository.findAll();
        Optional<OrderDocument> optional = orders.stream().reduce((a, b) -> a.getNumber() > b.getNumber() ? a : b);
        if(optional.isPresent())
            return optional.get().getNumber();
        else
            return 0;
    }

    private int getNumberOfOrderById(String id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id)).getNumber();
    }
}
