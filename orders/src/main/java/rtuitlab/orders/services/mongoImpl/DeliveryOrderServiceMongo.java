package rtuitlab.orders.services.mongoImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rtuitlab.orders.models.BoughtProductInfo;
import rtuitlab.orders.models.documents.DeliveryOrderDocument;
import rtuitlab.orders.dto.deliveryOrder.GetDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PostDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PutDeliveryOrderDTO;
import rtuitlab.orders.exceptions.DeliveryOrderNotFoundException;
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
        DeliveryOrderDocument deliveryOrderDocument = deliveryOrderRepository.findById(id).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
        return deliveryOrderMapper.entityToDTO(deliveryOrderDocument);
    }

    @Override
    public List<GetDeliveryOrderDTO> create(PostDeliveryOrderDTO deliveryOrder) {
        DeliveryOrderDocument deliveryOrderDocument = deliveryOrderMapper.postDTOToEntity(deliveryOrder);
        int cost = 0;
        for (BoughtProductInfo boughtProductInfo: deliveryOrderDocument.getProducts()) {
            cost += boughtProductInfo.getCost() * boughtProductInfo.getAmount();
        }
        deliveryOrderDocument.setCost(cost);
        deliveryOrderDocument.setOrderDate(new Date());
        deliveryOrderRepository.save(deliveryOrderDocument);
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
        DeliveryOrderDocument newDeliveryOrderDocument = deliveryOrderMapper.putDTOToEntity(deliveryOrder);
        newDeliveryOrderDocument.setId(id);
        int cost = 0;
        for (BoughtProductInfo boughtProductInfo: newDeliveryOrderDocument.getProducts()) {
            cost += boughtProductInfo.getCost() * boughtProductInfo.getAmount();
        }
        newDeliveryOrderDocument.setCost(cost);
        deliveryOrderRepository.save(newDeliveryOrderDocument);
        DeliveryOrderDocument updatedDeliveryOrderDocument = deliveryOrderRepository.findById(id).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
        return deliveryOrderMapper.entityToDTO(updatedDeliveryOrderDocument);
    }
}