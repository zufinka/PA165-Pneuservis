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
     * Finds all order items based on the search parameter which represents the search query.
     */
    List<OrderItemDTO> findOrderItems(OrderItemDTO search);

    /**
     * Retrieves all order items.
     */
    List<OrderItemDTO> getAllOrderItems();
}
