package rtuitlab.deliveries.config.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class OrdersListener {
    @RabbitListener(queues = "orders-deliveries-response-queue")
    public void processListeningOrdersQueue(String message) {
        System.out.println(message); //temporary
    }
}
