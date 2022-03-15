package rtuitlab.orders.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String id) {
        super("Entity with id = " + id + " is not found");
    }
}
