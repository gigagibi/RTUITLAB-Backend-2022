package rtuitlab.orders.services.mongoImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.orders.dto.order.GetOrderDTO;
import rtuitlab.orders.dto.order.PostOrderDTO;
import rtuitlab.orders.dto.order.PutOrderDTO;
import rtuitlab.orders.exceptions.OrderNotFoundException;
import rtuitlab.orders.mappers.OrderMapper;
import rtuitlab.orders.models.Order;
import rtuitlab.orders.repositories.OrderRepository;
import rtuitlab.orders.services.OrderService;

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
    public GetOrderDTO getById(int id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.entityToDTO(order);
    }

    @Override
    public List<GetOrderDTO> create(PostOrderDTO order) {
        orderRepository.save(orderMapper.postDTOToEntity(order));
        return orderRepository.findAll().stream().map(o -> orderMapper.entityToDTO(o)).collect(Collectors.toList());
    }

    @Override
    public List<GetOrderDTO> deleteById(int id) {
        orderRepository.deleteById(id);
        return orderRepository.findAll().stream().map(o -> orderMapper.entityToDTO(o)).collect(Collectors.toList());
    }

    @Override
    public GetOrderDTO update(int id, PutOrderDTO order) throws OrderNotFoundException {
        Order newOrder = orderMapper.putDTOToEntity(order);
        orderRepository.save(newOrder);
        Order updatedOrder = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.entityToDTO(updatedOrder);
    }
}
