package services;

import cz.muni.fi.pa165.pneuservis.backend.dao.OrderItemDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    @Inject
    private OrderItemDao orderItemDao;

    @Override
    public OrderItem getOrderItem(long id) {
        checkNotNull(id);
        return orderItemDao.getById(id);
    }

    @Override
    public void create(OrderItem orderItem) {
        checkNotNull(orderItem);
        orderItemDao.create(orderItem);
    }

    @Override
    public void update(OrderItem orderItem) {
        checkNotNull(orderItem);
        orderItemDao.update(orderItem);
    }

    @Override
    public void delete(OrderItem orderItem) {
        checkNotNull(orderItem);
        orderItemDao.delete(orderItem);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemDao.getAll();
    }
}
