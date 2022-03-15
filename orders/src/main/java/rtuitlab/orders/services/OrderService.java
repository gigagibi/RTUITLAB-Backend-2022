package rtuitlab.orders.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rtuitlab.orders.dto.order.*;
import rtuitlab.orders.exceptions.EntityNotFoundException;
import rtuitlab.orders.mappers.OrderMapper;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.OrderDocument;
import rtuitlab.orders.repositories.OrderRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService extends AbstractService<OrderDocument, OrderRepository, OrderGetDTO, OrderPostDTO, OrderPutDTO, OrderPostedDTO, OrderUpdatedDTO, OrderMapper> {

    public OrderService(OrderRepository repository, @Qualifier("orderMapperImpl") OrderMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public List<OrderPostedDTO> create(OrderPostDTO OrderPostDTO) {
        OrderDocument OrderDocument = mapper.postDTOToEntity(OrderPostDTO);
        int cost = 0;
        for (BoughtProductInfo boughtProductInfo: OrderDocument.getProducts()) {
            cost += boughtProductInfo.getCost() * boughtProductInfo.getAmount();
        }
        OrderDocument.setCost(cost);
        OrderDocument.setOrderDate(new Date());
        repository.save(OrderDocument);
        return repository.findAll().stream().map(mapper::entityToPostedDTO).collect(Collectors.toList());
    }

    @Override
    public OrderUpdatedDTO update(String id, OrderPutDTO OrderPutDTO) throws EntityNotFoundException {
        if(!repository.existsById(id))
            throw new EntityNotFoundException(id);
        OrderDocument OrderDocument = mapper.putDTOToEntity(OrderPutDTO);
        OrderDocument.setId(id);
        int cost = 0;
        for (BoughtProductInfo boughtProductInfo: OrderDocument.getProducts()) {
            cost += boughtProductInfo.getCost() * boughtProductInfo.getAmount();
        }
        OrderDocument.setCost(cost);
        repository.save(OrderDocument);
        OrderDocument updatedOrderDocument = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return mapper.entityToUpdatedDTO(updatedOrderDocument);
    }
}
