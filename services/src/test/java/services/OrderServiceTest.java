package services;

import config.OrderServiceTestConfig;
import cz.muni.fi.pa165.pneuservis.backend.entity.Order;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

public class OrderServiceTest{

    private OrderService orderService;

    @BeforeMethod
    public void setUp() {
        ApplicationContext apx = new AnnotationConfigApplicationContext(OrderServiceTestConfig.class);
        orderService = apx.getBean(OrderService.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNullOrderTest() {
        orderService.create(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void updateNullOrderTest() {
        orderService.update(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void deleteNullOrderTest() {
        orderService.delete(null);
    }

    @Test
    public void getAllOrdersTest() {
        List<Order> orders = orderService.getAllOrders();
        assertEquals(orders.size(), 3);
    }

    @Test
    public void getByNonExistentIdTest() {
        assertNull(orderService.getOrder(666L));
    }

    @Test
    public void findNonExistentOrderTest() {
        Order order = new Order();
        order.setId(666L);
        assertEquals(orderService.findOrders(order).size(), 0);
    }
}
