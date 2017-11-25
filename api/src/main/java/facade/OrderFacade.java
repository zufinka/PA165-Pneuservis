package facade;

import dto.OrderDTO;

import java.util.List;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */
public interface OrderFacade {
    /**
     * Retrieves an order based on ID.
     */
    OrderDTO getOrder(long id);

    /**
     * Finds all orders based on the search parameter which represents the search query.
     */
    List<OrderDTO> findOrders(OrderDTO search);

    /**
     * Retrieves all orders.
     */
    List<OrderDTO> getAllOrders();
}
