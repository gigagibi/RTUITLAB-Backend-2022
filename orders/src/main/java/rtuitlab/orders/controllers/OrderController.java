package rtuitlab.orders.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rtuitlab.orders.dto.order.GetOrderDTO;
import rtuitlab.orders.dto.order.PostOrderDTO;
import rtuitlab.orders.dto.order.PutOrderDTO;
import rtuitlab.orders.exceptions.OrderNotFoundException;
import rtuitlab.orders.services.OrderService;
import rtuitlab.orders.services.mongoImpl.OrderServiceMongo;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    @GetMapping("/")
    public List<GetOrderDTO> getOrders(){
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public GetOrderDTO getOrder(@PathVariable String id) {
        try {
            return orderService.getById(id);
        }
        catch (OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with given id is not found");
        }
    }

    @PostMapping("/")
    public List<GetOrderDTO> createOrder(@RequestBody PostOrderDTO postOrderDTO) {
        return orderService.create(postOrderDTO);
    }

    @PutMapping("/{id}")
    public GetOrderDTO updateOrder(@PathVariable String id, @RequestBody PutOrderDTO putOrderDTO) {
        try {
            return orderService.update(id, putOrderDTO);
        }
        catch (OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public List<GetOrderDTO> deleteOrder(@PathVariable String id) {
        try {
            return orderService.deleteById(id);
        }
        catch (OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
