package rtuitlab.products.exception.product;

import java.text.MessageFormat;

public class ProductWithGivenNameNotFoundException extends Exception {
    public ProductWithGivenNameNotFoundException(String name) {
        super(MessageFormat.format("Product with name = {0} is not found", name));
    }
}
