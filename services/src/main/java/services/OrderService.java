package services;

import cz.muni.fi.pa165.pneuservis.backend.entity.Order;

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
     * Finds all orders based on the search parameter which represents the search query.
     */
    List<Order> findOrders(Order search);

    /**
     * Retrieves all orders.
     */
    List<Order> getAllOrders();
}
