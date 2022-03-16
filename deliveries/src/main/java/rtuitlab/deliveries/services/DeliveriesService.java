package rtuitlab.deliveries.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import rtuitlab.deliveries.dto.orderRabbit.OrderFromOrdersDTO;
import rtuitlab.deliveries.dto.orderRabbit.OrderToOrdersDTO;
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

    public OrderFromOrdersDTO sendOrderToOrders(OrderToOrdersDTO orderToOrdersDTO) {
        OrderFromOrdersDTO orderFromOrdersDTO = new OrderFromOrdersDTO();
        ListenableFuture<OrderFromOrdersDTO> listenableFuture = asyncRabbitTemplate.convertSendAndReceiveAsType("orders-exchange", "orders", orderToOrdersDTO, new ParameterizedTypeReference<>() {
        });
        try {
            orderFromOrdersDTO = listenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return orderFromOrdersDTO;
    }
}
