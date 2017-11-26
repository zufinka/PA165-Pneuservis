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
     * Retrieves all order items.
     */
    List<OrderItemDTO> getAllOrderItems();
}
