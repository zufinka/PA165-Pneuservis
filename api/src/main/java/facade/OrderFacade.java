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
     * Creates an order.
     */
    void create(OrderDTO order);

    /**
     * Modifies the specified order.
     */
    void update(OrderDTO order);

    /**
     * Removes the specified order.
     */
    void delete(OrderDTO order);

    /**
     * Finds all orders based on the search parameter which represents the search query.
     */
    List<OrderDTO> findOrders(OrderDTO search);

    /**
     * Finds all orders of the specified customer.
     */
    List<OrderDTO> findAllOrdersOfCustomer(CustomerDTO search);

    /**
     * Finds all orders containing the specified order item.
     */
    List<OrderDTO> findAllOrdersContainingOrderItem(OrderItemDTO search);

    /**
     * Retrieves all orders.
     */
    List<OrderDTO> getAllOrders();
}
