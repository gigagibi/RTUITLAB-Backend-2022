package rtuitlab.supplies.exceptions;

import java.text.MessageFormat;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String id) {
        super("Entity with id = " + id + " is not found");
    }
}
