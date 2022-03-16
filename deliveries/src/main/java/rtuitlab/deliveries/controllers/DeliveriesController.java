package rtuitlab.deliveries.controllers;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import rtuitlab.deliveries.dto.orderRabbit.OrderFromOrdersDTO;
import rtuitlab.deliveries.dto.orderRabbit.OrderToOrdersDTO;
import rtuitlab.deliveries.dto.productRabbit.ProductReceiveFromProductsDTO;
import rtuitlab.deliveries.dto.user.UserInfoDTO;
import rtuitlab.deliveries.services.DeliveriesService;
import rtuitlab.deliveries.services.UserService;
import springfox.documentation.annotations.ApiIgnore;

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

    @PostMapping("/order/")
    public OrderFromOrdersDTO sendOrder(@ApiIgnore @RequestHeader("Authorization") String token, @RequestBody OrderToOrdersDTO orderToOrdersDTO) {
        UserInfoDTO userInfoDTO = userService.getByToken(token);
        orderToOrdersDTO.setAddress(userInfoDTO.getAddress());
        orderToOrdersDTO.setPhone(userInfoDTO.getPhone());
        return deliveriesService.sendOrderToOrders(orderToOrdersDTO);
    }
}
