package rtuitlab.deliveries.config.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class ProductsListener {
    @RabbitListener(queues = "products-deliveries-post-queue")
    public void processListeningProductsQueue(String message) {
        System.out.println(message); //temporary
    }
}
