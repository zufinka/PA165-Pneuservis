package services;

import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;

import java.util.List;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */
public interface OrderItemService {
    /**
     * Retrieves an order item based on ID.
     */
    OrderItem getOrderItem(long id);

    /**
     * Creates a new order item.
     */
    void create(OrderItem orderItem);

    /**
     * Modifies the specified order item.
     */
    void update(OrderItem orderItem);

    /**
     * Removes the specified order item.
     */
    void delete(OrderItem orderItem);

    /**
     * Retrieves all order items.
     */
    List<OrderItem> getAllOrderItems();
}
