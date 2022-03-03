package rtuitlab.orders.services.mongoImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.orders.dto.deliveryOrder.GetDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PostDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PutDeliveryOrderDTO;
import rtuitlab.orders.exceptions.DeliveryOrderNotFoundException;
import rtuitlab.orders.exceptions.OrderNotFoundException;
import rtuitlab.orders.models.DeliveryOrder;
import rtuitlab.orders.repositories.DeliveryOrderRepository;
import rtuitlab.orders.services.DeliveryOrderService;
import rtuitlab.orders.mappers.DeliveryOrderMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeliveryOrderServiceMongo implements DeliveryOrderService {
    private DeliveryOrderRepository deliveryOrderRepository;
    private DeliveryOrderMapper deliveryOrderMapper;
    @Override
    public List<GetDeliveryOrderDTO> getAll() {
        return deliveryOrderRepository.findAll().stream().map(o -> deliveryOrderMapper.entityToDTO(o)).collect(Collectors.toList());
    }

    @Override
    public GetDeliveryOrderDTO getById(int id) throws DeliveryOrderNotFoundException {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(id).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
        return deliveryOrderMapper.entityToDTO(deliveryOrder);
    }

    @Override
    public List<GetDeliveryOrderDTO> create(PostDeliveryOrderDTO deliveryOrder) {
        deliveryOrderRepository.save(deliveryOrderMapper.postDTOToEntity(deliveryOrder));
        return deliveryOrderRepository.findAll().stream().map(o -> deliveryOrderMapper.entityToDTO(o)).collect(Collectors.toList());
    }

    @Override
    public List<GetDeliveryOrderDTO> deleteById(int id) throws OrderNotFoundException {
        if(deliveryOrderRepository.findById(id).isEmpty())
            throw new OrderNotFoundException(id);
        deliveryOrderRepository.deleteById(id);
        return deliveryOrderRepository.findAll().stream().map(o -> deliveryOrderMapper.entityToDTO(o)).collect(Collectors.toList());
    }

    @Override
    public GetDeliveryOrderDTO update(int id, PutDeliveryOrderDTO deliveryOrder) throws DeliveryOrderNotFoundException {
        DeliveryOrder newDeliveryOrder = deliveryOrderMapper.putDTOToEntity(deliveryOrder);
        deliveryOrderRepository.save(newDeliveryOrder);
        DeliveryOrder updatedDeliveryOrder = deliveryOrderRepository.findById(id).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
        return deliveryOrderMapper.entityToDTO(updatedDeliveryOrder);
    }
}