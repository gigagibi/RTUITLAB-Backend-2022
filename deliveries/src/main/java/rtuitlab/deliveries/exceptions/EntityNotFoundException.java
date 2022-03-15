package rtuitlab.deliveries.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(int id) {
        super("Entity with id = " + id + " is not found");
    }
}
