package rtuitlab.orders.services.mongoImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.orders.dto.order.GetOrderDTO;
import rtuitlab.orders.dto.order.PostOrderDTO;
import rtuitlab.orders.dto.order.PutOrderDTO;
import rtuitlab.orders.exceptions.OrderNotFoundException;
import rtuitlab.orders.mappers.OrderMapper;
import rtuitlab.orders.models.documents.OrderDocument;
import rtuitlab.orders.repositories.OrderRepository;
import rtuitlab.orders.services.OrderService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceMongo implements OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    @Override
    public List<GetOrderDTO> getAll() {
        return orderRepository.findAll().stream().map(o -> orderMapper.entityToDTO(o)).collect(Collectors.toList());
    }

    @Override
    public GetOrderDTO getById(String id) throws OrderNotFoundException {
        OrderDocument orderDocument = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.entityToDTO(orderDocument);
    }

    @Override
    public List<GetOrderDTO> create(PostOrderDTO order) {
        OrderDocument orderDocument = orderMapper.postDTOToEntity(order);
        orderDocument.setOrderDate(new Date());
        orderRepository.save(orderDocument);
        return orderRepository.findAll().stream().map(o -> orderMapper.entityToDTO(o)).collect(Collectors.toList());
    }

    @Override
    public List<GetOrderDTO> deleteById(String id) throws OrderNotFoundException {
        if(orderRepository.findById(id).isEmpty())
            throw new OrderNotFoundException(id);
        orderRepository.deleteById(id);
        return orderRepository.findAll().stream().map(o -> orderMapper.entityToDTO(o)).collect(Collectors.toList());
    }

    @Override
    public GetOrderDTO update(String id, PutOrderDTO order) throws OrderNotFoundException {
        OrderDocument newOrderDocument = orderMapper.putDTOToEntity(order);
        newOrderDocument.setId(id);
        orderRepository.save(newOrderDocument);
        OrderDocument updatedOrderDocument = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.entityToDTO(updatedOrderDocument);
    }
}
