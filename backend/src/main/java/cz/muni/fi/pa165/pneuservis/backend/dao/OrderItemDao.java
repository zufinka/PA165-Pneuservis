package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Data Access Object specification for the class {@link OrderItem}
 * @author Martin Zilak, 433372@mail.muni.cz
 */

public interface OrderItemDao {

    /**
     * Retrieves an order item with the specified ID.
     * @param id of the requested order item.
     * @return retrieved order item or {@code null}, if there is no order item with the specified ID.
     */
    OrderItem getById(Long id);

    /**
     * Retrieves all order items.
     * @return list of retrieved order items.
     */
    List<OrderItem> getAll();

    /**
     * Stores a new order item in the database.
     * @param orderItem to be stored.
     * @throws NullPointerException if the {@code orderItem} is {@code null}.
     * @throws ConstraintViolationException if {@code orderItem} has {@code null} quantity or the quantity is less than 1.
     */
    void create(OrderItem orderItem);

    /**
     * Modifies the specified order item in the database.
     * @param orderItem to be modified.
     * @throws NullPointerException if the {@code orderItem} is {@code null}.
     * @throws ConstraintViolationException if {@code orderItem} has {@code null} quantity or the quantity is less than 1.
     */
    void update(OrderItem orderItem);

    /**
     * Removes the specified order item from the database.
     * @param orderItem to be removed.
     * @throws NullPointerException if the {@code orderItem} is {@code null}.
     * @throws ConstraintViolationException if {@code orderItem} has {@code null} quantity or the quantity is less than 1.
     */
    void delete(OrderItem orderItem);
}
