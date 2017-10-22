package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.Order;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Data Access Object specification for the class {@link Order}
 * @author Martin Zilak, 433372@mail.muni.cz
 */

public interface OrderDao {

    /**
     * Retrieves an order with the specified ID.
     * @param id of the requested order.
     * @return retrieved order or {@code null}, if there is no order with the specified ID.
     */
    Order getById(Long id);

    /**
     * Retrieves all orders.
     * @return list of retrieved orders.
     */
    List<Order> getAll();

    /**
     * Stores a new order in the database.
     * @param order to be stored.
     * @throws NullPointerException if the {@code order} is {@code null}.
     * @throws ConstraintViolationException if {@code order} has {@code null} date, customer or the list of order items.
     */
    void create(Order order);

    /**
     * Modifies the specified order in the database.
     * @param order to be modified.
     * @throws NullPointerException if the {@code order} is {@code null}.
     * @throws ConstraintViolationException if {@code order} has {@code null} date, customer or the list of order items.
     */
    void update(Order order);

    /**
     * Removes the specified order from the database.
     * @param order to be removed.
     * @throws NullPointerException if the {@code order} is {@code null}.
     * @throws ConstraintViolationException if {@code order} has {@code null} date, customer or the list of order items.
     */
    void delete(Order order);
}
