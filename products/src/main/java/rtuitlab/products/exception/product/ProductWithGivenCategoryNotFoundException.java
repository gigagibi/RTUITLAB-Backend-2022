package rtuitlab.products.exception.product;

import java.text.MessageFormat;

public class ProductWithGivenCategoryNotFoundException extends Exception {
    public ProductWithGivenCategoryNotFoundException(int id) {
        super(MessageFormat.format("Product with category.id = {0} is not found", id));
    }
}
