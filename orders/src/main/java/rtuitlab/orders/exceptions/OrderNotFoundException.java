package rtuitlab.orders.exceptions;

import java.text.MessageFormat;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String id) {
        super(MessageFormat.format("Order with id = {0} not found!", id));
    }
}
