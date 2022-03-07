package rtuitlab.products.exception.product;

import java.text.MessageFormat;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(int id) {
        super(MessageFormat.format("Product with id = {0} is not found", id));
    }
}
