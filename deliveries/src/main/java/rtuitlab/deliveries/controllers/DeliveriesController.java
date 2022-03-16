package rtuitlab.deliveries.controllers;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import rtuitlab.deliveries.dto.orderRabbit.OrderReceiveFromOrdersDTO;
import rtuitlab.deliveries.dto.orderRabbit.OrderSendToOrdersDTO;
import rtuitlab.deliveries.dto.productRabbit.ProductReceiveFromProductsDTO;
import rtuitlab.deliveries.dto.user.UserInfoDTO;
import rtuitlab.deliveries.services.DeliveriesService;
import rtuitlab.deliveries.services.UserService;

@RestController
@RequestMapping("/api/v1/deliveries")
@AllArgsConstructor
public class DeliveriesController {
    private final RabbitTemplate rabbitTemplate;
    private final AsyncRabbitTemplate asyncRabbitTemplate;
    private final DeliveriesService deliveriesService;
    private final UserService userService;
    @GetMapping("/product/{id}")
    public ProductReceiveFromProductsDTO getProduct(@PathVariable int id) {
        return deliveriesService.getProductFromProducts(id);
    }

    @GetMapping("/order/")
    public OrderReceiveFromOrdersDTO sendOrder(@RequestHeader("Authorization") String token, @RequestBody OrderSendToOrdersDTO orderSendToOrdersDTO) {
        UserInfoDTO userInfoDTO = userService.getByToken(token);
        orderSendToOrdersDTO.setAddress(userInfoDTO.getAddress());
        orderSendToOrdersDTO.setPhone(userInfoDTO.getPhone());
        return deliveriesService.sendOrderToOrders(orderSendToOrdersDTO);
    }
}
