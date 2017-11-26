package facade;

import dto.OrderItemDTO;

import java.util.List;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */
public interface OrderItemFacade {
    /**
     * Retrieves an order item based on ID.
     */
    OrderItemDTO getOrderItem(long id);

    /**
     * Creates a new order item.
     */
    void create(OrderItemDTO orderItem);

    /**
     * Modifies the specified order item.
     */
    void update(OrderItemDTO orderItem);

    /**
     * Removes the specified order item.
     */
    void delete(OrderItemDTO orderItem);

    /**
     * Retrieves all order items.
     */
    List<OrderItemDTO> getAllOrderItems();
}
