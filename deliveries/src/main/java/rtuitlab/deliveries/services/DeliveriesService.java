package rtuitlab.deliveries.services;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import rtuitlab.deliveries.dto.order.OrderReceiveDTO;
import rtuitlab.deliveries.dto.order.OrderSendDTO;
import rtuitlab.deliveries.dto.product.ProductReceiveDTO;

@Service
@AllArgsConstructor
public class DeliveriesService {
    private final AmqpTemplate amqpTemplate;

    public ProductReceiveDTO getProductFromProducts(Integer id) {
        amqpTemplate.convertAndSend("products-exchange", "products", id);
        return new ProductReceiveDTO(); //temporary
    }

    public OrderReceiveDTO sendOrderToOrders(OrderSendDTO orderSendDTO) {
        amqpTemplate.convertAndSend("orders-exchange", "orders", orderSendDTO);
        return new OrderReceiveDTO(); //temporary
    }
}
