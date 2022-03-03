package rtuitlab.orders.services;

import rtuitlab.orders.dto.deliveryOrder.PostDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PutDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.GetDeliveryOrderDTO;
import rtuitlab.orders.exceptions.DeliveryOrderNotFoundException;
import rtuitlab.orders.exceptions.OrderNotFoundException;

import java.util.List;

public interface DeliveryOrderService {
    List<GetDeliveryOrderDTO> getAll();

    GetDeliveryOrderDTO getById(int id) throws DeliveryOrderNotFoundException;

    List<GetDeliveryOrderDTO> create(PostDeliveryOrderDTO order);

    List<GetDeliveryOrderDTO> deleteById(int id) throws OrderNotFoundException;

    GetDeliveryOrderDTO update(int id, PutDeliveryOrderDTO order) throws DeliveryOrderNotFoundException;
}
