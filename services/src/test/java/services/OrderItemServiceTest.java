package services;

import config.*;
import cz.muni.fi.pa165.pneuservis.backend.dao.OrderItemDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.*;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
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