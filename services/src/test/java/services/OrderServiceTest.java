package services;

import config.OrderServiceTestConfig;
import config.ServiceConfiguration;
import config.TireDataConfig;
import cz.muni.fi.pa165.pneuservis.backend.dao.OrderDao;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
@ContextConfiguration(classes = {ServiceConfiguration.class, TireDataConfig.class})
public class OrderServiceTest extends AbstractTestNGSpringContextTests {

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
