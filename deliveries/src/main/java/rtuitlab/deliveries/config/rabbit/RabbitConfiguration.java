package rtuitlab.deliveries.config.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Value("${rabbitmq.host}")
    private String host;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public DirectExchange productsExchange() {
        return new DirectExchange("products-exchange");
    }

    @Bean
    public Queue deliveriesProductsGetQueue() {
        return new Queue("deliveries-products-get-queue");
    }

    @Bean
    public Queue productsDeliveriesPostQueue() {
        return new Queue("products-deliveries-post-queue");
    }

    @Bean
    public Binding productsBindingGet() {
        return BindingBuilder.bind(deliveriesProductsGetQueue()).to(productsExchange()).with("products");
    }

    @Bean
    public DirectExchange ordersExchange() {
        return new DirectExchange("orders-exchange");
    }

    @Bean
    public Queue deliveriesOrdersPostQueue() {
        return new Queue("deliveries-orders-post-queue");
    }

    @Bean
    public Queue ordersDeliveriesResponseQueue() {
        return new Queue("orders-deliveries-response-queue");
    }

    @Bean
    public Binding sendOrderRequestBinding() {
        return BindingBuilder.bind(deliveriesOrdersPostQueue()).to(ordersExchange()).with("orders");
    }
}
