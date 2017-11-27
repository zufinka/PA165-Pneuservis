package services;

import config.OrderItemServiceTestConfig;
import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public class OrderItemServiceTest{

    private OrderItemService orderItemService;

    @BeforeClass
    public void setUp() {
        ApplicationContext apx = new AnnotationConfigApplicationContext(OrderItemServiceTestConfig.class);
        orderItemService = apx.getBean(OrderItemService.class);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNullOrderItemTest() {
        orderItemService.create(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void updateNullOrderItemTest() {
        orderItemService.update(null);
    }

    @Test
    public void getByNonExistentIdTest() {
        assertNull(orderItemService.getOrderItem(666L));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void deleteNullOrderItemTest() {
        orderItemService.delete(null);
    }

    @Test
    public void getAllOrderItemsTest() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        assertEquals(orderItems.size(), 2);
    }
}