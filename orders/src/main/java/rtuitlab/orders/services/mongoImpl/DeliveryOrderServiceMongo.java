package rtuitlab.orders.services.mongoImpl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rtuitlab.orders.dto.deliveryOrder.GetDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PostDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PutDeliveryOrderDTO;
import rtuitlab.orders.exceptions.DeliveryOrderNotFoundException;
import rtuitlab.orders.models.DeliveryOrderEntity;
import rtuitlab.orders.models.OrderEntity;
import rtuitlab.orders.repositories.DeliveryOrderRepository;
import rtuitlab.orders.services.DeliveryOrderService;
import rtuitlab.orders.mappers.DeliveryOrderMapper;

import java.util.Date;
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
    public GetDeliveryOrderDTO getById(String id) throws DeliveryOrderNotFoundException {
        DeliveryOrderEntity deliveryOrderEntity = deliveryOrderRepository.findById(id).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
        return deliveryOrderMapper.entityToDTO(deliveryOrderEntity);
    }

    @Override
    public List<GetDeliveryOrderDTO> create(PostDeliveryOrderDTO deliveryOrder) {
        DeliveryOrderEntity deliveryOrderEntity = deliveryOrderMapper.postDTOToEntity(deliveryOrder);
        deliveryOrderEntity.setOrderDate(new Date());
        deliveryOrderRepository.save(deliveryOrderEntity);
        return deliveryOrderRepository.findAll().stream().map(o -> deliveryOrderMapper.entityToDTO(o)).collect(Collectors.toList());
    }

    @Override
    public List<GetDeliveryOrderDTO> deleteById(String id) throws DeliveryOrderNotFoundException {
        if(deliveryOrderRepository.findById(id).isEmpty())
            throw new DeliveryOrderNotFoundException(id);
        deliveryOrderRepository.deleteById(id);
        return deliveryOrderRepository.findAll().stream().map(o -> deliveryOrderMapper.entityToDTO(o)).collect(Collectors.toList());
    }

    @Override
    public GetDeliveryOrderDTO update(String id, PutDeliveryOrderDTO deliveryOrder) throws DeliveryOrderNotFoundException {
        DeliveryOrderEntity newDeliveryOrderEntity = deliveryOrderMapper.putDTOToEntity(deliveryOrder);
        newDeliveryOrderEntity.setId(id);
        deliveryOrderRepository.save(newDeliveryOrderEntity);
        DeliveryOrderEntity updatedDeliveryOrderEntity = deliveryOrderRepository.findById(id).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
        return deliveryOrderMapper.entityToDTO(updatedDeliveryOrderEntity);
    }
}