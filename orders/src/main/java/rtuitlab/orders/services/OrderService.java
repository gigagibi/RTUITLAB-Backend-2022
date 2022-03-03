package rtuitlab.orders.services;

import rtuitlab.orders.dto.order.GetOrderDTO;
import rtuitlab.orders.dto.order.PostOrderDTO;
import rtuitlab.orders.dto.order.PutOrderDTO;
import rtuitlab.orders.exceptions.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    List<GetOrderDTO> getAll();

    GetOrderDTO getById(String id) throws OrderNotFoundException;

    List<GetOrderDTO> create(PostOrderDTO order);

    List<GetOrderDTO> deleteById(String id) throws OrderNotFoundException;

    GetOrderDTO update(String id, PutOrderDTO order) throws OrderNotFoundException;
}
