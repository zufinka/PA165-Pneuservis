package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;

import java.util.List;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */

public interface OrderItemDao {

    OrderItem getById(Long id);

    List<OrderItem> getAll();

    void create(OrderItem order);

    void update(OrderItem order);

    void delete(OrderItem order);
}
