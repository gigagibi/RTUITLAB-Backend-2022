package rtuitlab.products.exception.category;

import java.text.MessageFormat;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(int id) {
        super(MessageFormat.format("Category with id = {0} is not found", id));
    }
}
