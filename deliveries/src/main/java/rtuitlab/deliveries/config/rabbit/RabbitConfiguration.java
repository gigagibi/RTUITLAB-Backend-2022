package rtuitlab.deliveries.config.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    @Autowired
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, CustomJacksonMessageConverter customJacksonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(customJacksonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    @Autowired
    public AsyncRabbitTemplate asyncRabbitTemplate(
            RabbitTemplate rabbitTemplate){
        return new AsyncRabbitTemplate(rabbitTemplate);
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
    public Queue deliveriesProductsGetAllQueue() {
        return new Queue("deliveries-products-get-all-queue");
    }

    @Bean
    @Autowired
    public Binding productsBindingGet(Queue deliveriesProductsGetQueue, DirectExchange productsExchange) {
        return BindingBuilder.bind(deliveriesProductsGetQueue).to(productsExchange).with("products");
    }

    @Bean
    @Autowired
    public Binding productsBindingGetAll(Queue deliveriesProductsGetAllQueue, DirectExchange productsExchange) {
        return BindingBuilder.bind(deliveriesProductsGetAllQueue).to(productsExchange).with("products-all");
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
    @Autowired
    public Binding sendOrderRequestBinding(Queue deliveriesOrdersPostQueue, DirectExchange ordersExchange) {
        return BindingBuilder.bind(deliveriesOrdersPostQueue).to(ordersExchange).with("orders");
    }
}
