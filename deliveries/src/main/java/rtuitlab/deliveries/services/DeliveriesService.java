package rtuitlab.deliveries.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import rtuitlab.deliveries.dto.orderRabbit.OrderFromOrdersRabbitDTO;
import rtuitlab.deliveries.dto.orderRabbit.OrderToOrdersRabbitDTO;
import rtuitlab.deliveries.dto.productRabbit.ProductFromProductsRabbitDTO;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class DeliveriesService {
    private final AsyncRabbitTemplate asyncRabbitTemplate;

    public ProductFromProductsRabbitDTO getProductFromProducts(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductFromProductsRabbitDTO productFromProductsRabbitDTO = new ProductFromProductsRabbitDTO();
        ListenableFuture<ProductFromProductsRabbitDTO> listenableFuture = asyncRabbitTemplate.convertSendAndReceiveAsType("products-exchange", "products", id, new ParameterizedTypeReference<>() {
        });
        try {
            productFromProductsRabbitDTO = listenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return productFromProductsRabbitDTO;
    }

    public OrderFromOrdersRabbitDTO sendOrderToOrders(OrderToOrdersRabbitDTO orderToOrdersRabbitDTO) {
        OrderFromOrdersRabbitDTO orderFromOrdersRabbitDTO = new OrderFromOrdersRabbitDTO();
        ListenableFuture<OrderFromOrdersRabbitDTO> listenableFuture = asyncRabbitTemplate.convertSendAndReceiveAsType("orders-exchange", "orders", orderToOrdersRabbitDTO, new ParameterizedTypeReference<>() {
        });
        try {
            orderFromOrdersRabbitDTO = listenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return orderFromOrdersRabbitDTO;
    }
}
