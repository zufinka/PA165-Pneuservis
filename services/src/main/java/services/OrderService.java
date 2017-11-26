package services;

import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;
import cz.muni.fi.pa165.pneuservis.backend.entity.Order;
import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;

import java.util.List;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */
public interface OrderService {
    /**
     * Retrieves an order based on ID.
     */
    Order getOrder(long id);

    /**
     * Creates an order.
     */
    void create(Order order);

    /**
     * Modifies the specified order.
     */
    void update(Order order);

    /**
     * Removes the specified order.
     */
    void delete(Order order);

    /**
     * Finds all orders based on the search parameter which represents the search query.
     */
    List<Order> findOrders(Order search);

    /**
     * Finds all orders of the specified customer.
     */
    List<Order> findAllOrdersOfCustomer(Customer search);

    /**
     * Finds all orders containing the specified order item.
     */
    List<Order> findAllOrdersContainingOrderItem(OrderItem search);

    /**
     * Retrieves all orders.
     */
    List<Order> getAllOrders();
}
