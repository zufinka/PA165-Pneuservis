package facade;

import dto.CustomerDTO;
import dto.OrderDTO;
import dto.OrderItemDTO;

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
     * Finds all orders of a specified customer.
     */
    List<OrderDTO> findAllOrdersOfCustomer(CustomerDTO search);

    /**
     * Finds all orders containing a specified order item.
     */
    List<OrderDTO> findAllOrdersContainingOrderItem(OrderItemDTO search);

    /**
     * Retrieves all orders.
     */
    List<OrderDTO> getAllOrders();
}
