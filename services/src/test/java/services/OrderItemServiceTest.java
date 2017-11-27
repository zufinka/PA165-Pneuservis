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
    private OrderItem orderItem1;
    private OrderItem orderItem2;
    private Tire tire;
    private Service service;

    @BeforeClass
    public void setUp() {
        prepareOrderItem();
        MockitoAnnotations.initMocks(this);
        ApplicationContext apx = new AnnotationConfigApplicationContext(OrderItemServiceTestConfig.class);
        orderItemService = apx.getBean(OrderItemService.class);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNullOrderItemTest() {
        orderItemService.create(null);
    }

    @Test
    public void createOrderItemTest() {
        orderItemService.create(orderItem1);
        //verify(orderItemDao).create(orderItem1);
        //verifyNoMoreInteractions(orderItemDao);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void updateNullOrderItemTest() {
        orderItemService.update(null);
    }

    @Test
    public void updateOrderItemTest() {
        orderItem1.setQuantity(1L);
        orderItemService.update(orderItem1);
        OrderItem o = orderItemService.getOrderItem(orderItem1.getId());
        assertEquals(o, orderItem1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void deleteNullOrderItemTest() {
        orderItemService.delete(null);
    }

    @Test
    public void deleteOrderItemTest() {
        orderItemService.create(orderItem1);
        //verify(orderItemDao).create(orderItem1);
        orderItemService.delete(orderItem1);
        //verify(orderItemDao).delete(orderItem1);
        assertNull(orderItemService.getOrderItem(orderItem1.getId()));
    }

    @Test
    public void getAllOrderItemsTest() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        assertEquals(orderItems.size(), 2);
    }

    private void prepareOrderItem() {
        TireManufacturer tireManufacturer = new TireManufacturer("Continental");
        TireProperties tireProperties = new TireProperties(VehicleTypeEnum.P, 165, 95, 16, 95, SpeedClassEnum.H, SeasonEnum.WINTER);
        tire = new Tire("WXR", tireManufacturer, tireProperties, 10, new BigDecimal(10.568));

        service = new Service("test service", TypeOfServiceEnum.TIRECHANGE);
        service.setPrice(BigDecimal.ONE);

        orderItem1 = new OrderItem(tire, 5L);
        orderItem2 = new OrderItem(service, 4L);
    }
}