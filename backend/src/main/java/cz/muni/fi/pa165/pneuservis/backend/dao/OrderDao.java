package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.Order;

import java.util.List;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */

public interface OrderDao {

    Order getById(Long id);

    List<Order> getAll();

    void create(Order order);

    void update(Order order);

    void delete(Order order);
}
