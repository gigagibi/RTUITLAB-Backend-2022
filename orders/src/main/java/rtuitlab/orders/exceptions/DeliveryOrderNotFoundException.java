package rtuitlab.orders.exceptions;

import java.text.MessageFormat;

public class DeliveryOrderNotFoundException extends Exception{
    public DeliveryOrderNotFoundException(String id) {
        super(MessageFormat.format("Application with id = {0} not found!", id));
    }
}
