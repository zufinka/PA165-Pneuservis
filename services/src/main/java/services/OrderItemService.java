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
     * Retrieves all order items.
     */
    List<OrderItem> getAllOrderItems();
}
