package rtuitlab.deliveries.controllers;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import rtuitlab.deliveries.dto.orderRabbit.OrderFromOrdersRabbitDTO;
import rtuitlab.deliveries.dto.orderRabbit.OrderToOrdersRabbitDTO;
import rtuitlab.deliveries.dto.productRabbit.ProductFromProductsRabbitDTO;
import rtuitlab.deliveries.dto.user.UserInfoDTO;
import rtuitlab.deliveries.services.DeliveriesService;
import rtuitlab.deliveries.services.UserService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deliveries")
@AllArgsConstructor
public class DeliveriesController {
    private final RabbitTemplate rabbitTemplate;
    private final AsyncRabbitTemplate asyncRabbitTemplate;
    private final DeliveriesService deliveriesService;
    private final UserService userService;

    @GetMapping("/products/{id}")
    public ProductFromProductsRabbitDTO getProduct(@PathVariable int id) {
        return deliveriesService.getProductByIdFromProducts(id);
    }

    @GetMapping("/products")
    public List<ProductFromProductsRabbitDTO> getProducts() {
        return deliveriesService.getAllProductsFromProducts();
    }

    @PostMapping("/order/")
    public OrderFromOrdersRabbitDTO sendOrder(@ApiIgnore @RequestHeader("Authorization") String token, @RequestBody OrderToOrdersRabbitDTO orderToOrdersRabbitDTO) {
        UserInfoDTO userInfoDTO = userService.getByToken(token);
        return deliveriesService.sendOrderToOrders(userInfoDTO.getAddress(), userInfoDTO.getPhone(), orderToOrdersRabbitDTO);
    }
}
