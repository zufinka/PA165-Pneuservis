package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */

public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order getById(Long id) {
        checkNotNull(id);
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> getAll() {
        return entityManager.createQuery("SELECT o FROM OrderTable o", Order.class).getResultList();
    }

    @Override
    public void create(Order order) {
        checkNotNull(order);
        entityManager.persist(order);
    }

    @Override
    public void update(Order order) {
        checkNotNull(order);
        entityManager.merge(order);
    }

    @Override
    public void delete(Order order) {
        checkNotNull(order);
        entityManager.remove(order);
    }
}
