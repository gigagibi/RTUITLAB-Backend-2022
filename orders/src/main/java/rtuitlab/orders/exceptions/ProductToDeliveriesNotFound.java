package rtuitlab.orders.exceptions;

public class ProductToDeliveriesNotFound extends Exception {
    public ProductToDeliveriesNotFound(int id) {
        super("Product with id = " + id + " is not found in products service");
    }
}
