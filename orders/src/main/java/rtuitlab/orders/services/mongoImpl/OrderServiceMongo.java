package rtuitlab.orders.services.mongoImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.orders.dto.order.GetOrderDTO;
import rtuitlab.orders.dto.order.PostOrderDTO;
import rtuitlab.orders.dto.order.PutOrderDTO;
import rtuitlab.orders.exceptions.OrderNotFoundException;
import rtuitlab.orders.mappers.OrderMapper;
import rtuitlab.orders.models.OrderEntity;
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
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.entityToDTO(orderEntity);
    }

    @Override
    public List<GetOrderDTO> create(PostOrderDTO order) {
        OrderEntity orderEntity = orderMapper.postDTOToEntity(order);
        orderEntity.setOrderDate(new Date());
        orderRepository.save(orderEntity);
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
        OrderEntity newOrderEntity = orderMapper.putDTOToEntity(order);
        newOrderEntity.setId(id);
        orderRepository.save(newOrderEntity);
        OrderEntity updatedOrderEntity = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.entityToDTO(updatedOrderEntity);
    }
}
