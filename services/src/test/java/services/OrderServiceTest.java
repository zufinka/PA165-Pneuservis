package services;

import config.ServiceConfiguration;
import cz.muni.fi.pa165.pneuservis.backend.dao.OrderDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.*;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderServiceTest extends AbstractTestNGSpringContextTests {

    public static final Long order1Id = 31L;
    public static final Long order2Id = 9L;
    public static final Long nonExistentOrderId = 666L;

    @Mock
    private OrderDao orderDao;

    @Inject
    @InjectMocks
    private OrderService orderService;
    private LocalDateTime date1, date2;
    private Tire tire;
    private Service service;
    private List<OrderItem> orderList;
    private Customer customer1, customer2;
    private Order order1, order2;

    @BeforeMethod
    public void setUp() {
        prepareOrder();
        MockitoAnnotations.initMocks(this);

        when(orderDao.getById(order1Id)).thenReturn(order1);
        when(orderDao.getById(order2Id)).thenReturn(order2);
        when(orderDao.getAll()).thenReturn(Arrays.asList(order1, order2));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            Order order = (Order) argument;
            if (order.getId() != null) {
                throw new NullPointerException("ID must be null when creating.");
            }
            order.setId(order1Id);
            return null;
        }).when(orderDao).create(any(Order.class));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            Order order = (Order) argument;
            if (order.getId() == null) {
                throw new NullPointerException("ID can't be null when updating.");
            }
            return null;
        }).when(orderDao).update(any(Order.class));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            Order order = (Order) argument;
            if (order.getId() == null) {
                throw new NullPointerException("ID can't be null when deleting.");
            }
            orderDao.getById(order.getId());
            return null;
        }).when(orderDao).delete(any(Order.class));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNullOrderTest() {
        orderService.create(null);
    }

    @Test
    public void createOrderTest() {
        orderService.create(order1);
        verify(orderDao).create(order1);
        verifyNoMoreInteractions(orderDao);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void updateNullOrderTest() {
        orderService.update(null);
    }

    @Test
    public void updateOrderTest() {
        order1.setCustomer(customer2);
        orderService.update(order1);
        Order order = orderService.getOrder(order1.getId());
        assertEquals(order, order1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void deleteNullOrderTest() {
        orderService.delete(null);
    }

    @Test
    public void deleteOrderTest() {
        orderService.create(order1);
        verify(orderDao).create(order1);
        orderService.delete(order1);
        verify(orderDao).delete(order1);
        assertNull(orderService.getOrder(order1.getId()));
    }

    @Test
    public void getAllOrdersTest() {
        List<Order> orders = orderService.getAllOrders();
        assertEquals(orders.size(), 2);
    }

    @Test
    public void getByNonExistentIdTest() {
        Order order = orderService.getOrder(nonExistentOrderId);
        assertNull(order);
    }

    @Test
    public void findNonExistentOrderTest() {
        Order order = new Order();
        order.setId(nonExistentOrderId);
        assertEquals(orderService.findOrders(order).size(), 0);
    }

    @Test
    public void findOrdersTest() {
        Order order = new Order();
        order.setCustomer(customer1);
        assertEquals(orderService.findOrders(order), Arrays.asList(order1));
    }

    @Test
    public void findAllOrdersOfCustomerTest() {
        order2.setCustomer(customer1);
        orderService.update(order2);
        assertEquals(orderService.findAllOrdersOfCustomer(customer1).size(), 2);
    }

    private void prepareOrder() {
        String dateString1 = "08.02.1995 12:35";
        String dateString2 = "11.07.1995 07:41";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        date1 = LocalDateTime.parse(dateString1, formatter);
        date2 = LocalDateTime.parse(dateString2, formatter);

        TireManufacturer tireManufacturer = new TireManufacturer("Continental");
        TireProperties tireProperties = new TireProperties(VehicleTypeEnum.P, 165, 95, 16, 95, SpeedClassEnum.H, SeasonEnum.WINTER);
        tire = new Tire("WXR", tireManufacturer, tireProperties, 10, new BigDecimal(10.568));

        service = new Service("test service", TypeOfServiceEnum.TIRECHANGE);
        service.setPrice(BigDecimal.ONE);

        OrderItem orderItem1 = new OrderItem(tire, 5L);
        OrderItem orderItem2 = new OrderItem(service, 4L);

        orderList = new ArrayList<>();
        orderList.add(orderItem1);
        orderList.add(orderItem2);

        customer1 = new Customer("test", "test", "test", "test", "test", "test", "test", "test");
        customer2 = new Customer("blah", "blah", "blah", "blah", "blah", "blah", "blah", "blah");

        order1 = new Order(date1, customer1, orderList);
        order2 = new Order(date2, customer2, orderList);
    }
}
