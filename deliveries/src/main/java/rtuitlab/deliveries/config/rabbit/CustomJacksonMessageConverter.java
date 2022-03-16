package rtuitlab.deliveries.config.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomJacksonMessageConverter extends Jackson2JsonMessageConverter {
    public CustomJacksonMessageConverter() {
        super();
    }

    @Override
    public Object fromMessage(Message message) {
        message.getMessageProperties().setContentType("application/json");
        return super.fromMessage(message);
    }
}
