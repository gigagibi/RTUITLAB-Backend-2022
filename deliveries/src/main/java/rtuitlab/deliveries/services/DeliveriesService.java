package rtuitlab.deliveries.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import rtuitlab.deliveries.dto.orderRabbit.OrderReceiveFromOrdersDTO;
import rtuitlab.deliveries.dto.orderRabbit.OrderSendToOrdersDTO;
import rtuitlab.deliveries.dto.productRabbit.ProductReceiveFromProductsDTO;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class DeliveriesService {
    private final AsyncRabbitTemplate asyncRabbitTemplate;

    public ProductReceiveFromProductsDTO getProductFromProducts(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductReceiveFromProductsDTO productReceiveFromProductsDTO = new ProductReceiveFromProductsDTO();
        ListenableFuture<ProductReceiveFromProductsDTO> listenableFuture = asyncRabbitTemplate.convertSendAndReceiveAsType("products-exchange", "products", id, new ParameterizedTypeReference<>() {
        });
        try {
            productReceiveFromProductsDTO = listenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return productReceiveFromProductsDTO;
    }

    public OrderReceiveFromOrdersDTO sendOrderToOrders(OrderSendToOrdersDTO orderSendToOrdersDTO) {
        OrderReceiveFromOrdersDTO orderReceiveFromOrdersDTO = new OrderReceiveFromOrdersDTO();
        ListenableFuture<OrderReceiveFromOrdersDTO> listenableFuture = asyncRabbitTemplate.convertSendAndReceiveAsType("products-exchange", "products", orderSendToOrdersDTO, new ParameterizedTypeReference<>() {
        });
        try {
            orderReceiveFromOrdersDTO = listenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return orderReceiveFromOrdersDTO;
    }
}
