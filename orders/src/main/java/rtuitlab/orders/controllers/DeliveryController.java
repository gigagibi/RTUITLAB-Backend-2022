package rtuitlab.orders.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.orders.dto.deliveryOrder.GetDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PostDeliveryOrderDTO;
import rtuitlab.orders.dto.deliveryOrder.PutDeliveryOrderDTO;
import rtuitlab.orders.exceptions.DeliveryOrderNotFoundException;
import rtuitlab.orders.services.DeliveryOrderService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/delivery_order")
public class DeliveryController {
    private DeliveryOrderService deliveryOrderService;

    @GetMapping("/")
    public List<GetDeliveryOrderDTO> getDeliveryOrders(){
        return deliveryOrderService.getAll();
    }

    @GetMapping("/{id}")
    public GetDeliveryOrderDTO getDeliveryOrder(@PathVariable int id) {
        try {
            return deliveryOrderService.getById(id);
        }
        catch (DeliveryOrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/")
    public List<GetDeliveryOrderDTO> createDeliveryOrder(@RequestBody PostDeliveryOrderDTO postDeliveryOrderDTO) {
        return deliveryOrderService.create(postDeliveryOrderDTO);
    }

    @PutMapping("/{id}")
    public GetDeliveryOrderDTO updateDeliveryOrder(@PathVariable int id, @RequestBody PutDeliveryOrderDTO putDeliveryOrderDTO) {
        try {
            return deliveryOrderService.update(id, putDeliveryOrderDTO);
        }
        catch (DeliveryOrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public List<GetDeliveryOrderDTO> deleteDeliveryOrder(@PathVariable int id) {
        try {
            return deliveryOrderService.deleteById(id);
        }
        catch (DeliveryOrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
