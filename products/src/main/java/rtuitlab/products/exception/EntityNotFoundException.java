package rtuitlab.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(int id) {
        super("Entity with id = " + id + " is not found");
    }
}
