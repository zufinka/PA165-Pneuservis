package services;

import cz.muni.fi.pa165.pneuservis.backend.dao.OrderDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;
import cz.muni.fi.pa165.pneuservis.backend.entity.Order;
import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Override
    public Order getOrder(long id) {
        checkNotNull(id);
        return orderDao.getById(id);
    }

    @Override
    public void create(Order order) {
        checkNotNull(order);
        orderDao.create(order);
    }

    @Override
    public void update(Order order) {
        checkNotNull(order);
        orderDao.update(order);
    }

    @Override
    public void delete(Order order) {
        checkNotNull(order);
        orderDao.delete(order);
    }

    @Override
    public List<Order> findOrders(Order search) {
        List<Order> orders = orderDao.getAll();
        return filterOrders(orders, search);
    }

    @Override
    public List<Order> findAllOrdersOfCustomer(Customer search) {
        checkNotNull(search);
        Order filter = new Order();
        filter.setCustomer(search);
        return findOrders(filter);
    }

    @Override
    public List<Order> findAllOrdersContainingOrderItem(OrderItem search) {
        checkNotNull(search);
        List<Order> orders = new ArrayList<>();
        for (Order order : getAllOrders()) {
            if (order.getOrderItems().contains(search)) {
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrders() {
        return Collections.unmodifiableList(orderDao.getAll());
    }

    private static List<Order> filterOrders(List<Order> orders, Order filter) {
        List<Order> fulfilling = new ArrayList<>();

        for (Order order : orders) {
            if (filter.getId() != null) {
                if (!filter.getId().equals(order.getId())) {
                    continue;
                }
            }
            if (filter.getDate() != null) {
                if (!filter.getDate().equals(order.getDate())) {
                    continue;
                }
            }
            if (filter.getCustomer() != null) {
                if (!filter.getCustomer().equals(order.getCustomer())) {
                    continue;
                }
            }
            if (filter.getOrderItems() != null) {
                if (!filter.getOrderItems().equals(order.getOrderItems())) {
                    continue;
                }
            }

            fulfilling.add(order);
        }

        return fulfilling;
    }
}
