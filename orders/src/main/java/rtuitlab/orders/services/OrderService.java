package rtuitlab.orders.services;

import rtuitlab.orders.dto.order.GetOrderDTO;
import rtuitlab.orders.dto.order.PostOrderDTO;
import rtuitlab.orders.dto.order.PutOrderDTO;
import rtuitlab.orders.exceptions.OrderNotFoundException;
import rtuitlab.orders.models.Order;

import java.util.List;

public interface OrderService {
    List<GetOrderDTO> getAll();

    GetOrderDTO getById(int id) throws OrderNotFoundException;

    List<GetOrderDTO> create(PostOrderDTO order);

    List<GetOrderDTO> deleteById(int id);

    GetOrderDTO update(int id, PutOrderDTO order) throws OrderNotFoundException;
}
