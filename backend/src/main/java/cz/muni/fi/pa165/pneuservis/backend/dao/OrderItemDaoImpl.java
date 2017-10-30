package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */
@Repository
@Transactional
public class OrderItemDaoImpl implements OrderItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OrderItem getById(Long id) {
        checkNotNull(id);
        return entityManager.find(OrderItem.class, id);
    }

    @Override
    public List<OrderItem> getAll() {
        return entityManager.createQuery("SELECT o FROM OrderItem o").getResultList();
    }

    @Override
    public void create(OrderItem orderItem) {
        checkNotNull(orderItem);
        entityManager.persist(orderItem);
    }

    @Override
    public void update(OrderItem orderItem) {
        checkNotNull(orderItem);
        entityManager.merge(orderItem);
    }

    @Override
    public void delete(OrderItem orderItem) {
        checkNotNull(orderItem);
        entityManager.remove(orderItem);
    }
}
