package rtuitlab.orders.services;

import org.springframework.stereotype.Service;
import rtuitlab.orders.dto.deliveryOrder.PostDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PutDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.GetDeliveryOrderDTO;
import rtuitlab.orders.exceptions.DeliveryOrderNotFoundException;
import rtuitlab.orders.exceptions.OrderNotFoundException;

import java.util.List;

public interface DeliveryOrderService {
    List<GetDeliveryOrderDTO> getAll();

    GetDeliveryOrderDTO getById(String id) throws DeliveryOrderNotFoundException;

    List<GetDeliveryOrderDTO> create(PostDeliveryOrderDTO order);

    List<GetDeliveryOrderDTO> deleteById(String id) throws DeliveryOrderNotFoundException;

    GetDeliveryOrderDTO update(String id, PutDeliveryOrderDTO order) throws DeliveryOrderNotFoundException;
}
