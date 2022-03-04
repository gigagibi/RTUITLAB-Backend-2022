package rtuitlab.products.exception;

import java.text.MessageFormat;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(int id) {
        super(MessageFormat.format("Product with id = {0} not found!", id));
    }
}
