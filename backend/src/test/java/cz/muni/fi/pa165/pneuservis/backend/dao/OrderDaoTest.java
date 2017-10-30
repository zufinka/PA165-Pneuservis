package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.PersistenceApplicationContext;
import cz.muni.fi.pa165.pneuservis.backend.dao.OrderDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;
import cz.muni.fi.pa165.pneuservis.backend.entity.Order;
import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class OrderDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private OrderDao odi;

    @PersistenceContext
    EntityManager entityManager;

    private Order order1;
    private Order order2;
    private Order order3;


    @BeforeMethod
    public void prepareOrders() {
        String dateString = "08.02.1995 12:35";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);

        TireManufacturer tm = new TireManufacturer("Continental");
        TireProperties tp = new TireProperties(VehicleTypeEnum.P, 165, 95, 16, 95, SpeedClassEnum.H, SeasonEnum.WINTER);
        Tire tire = new Tire("WXR", tm, tp, 10, new BigDecimal(10.568));
        entityManager.persist(tm);
        entityManager.persist(tp);
        entityManager.persist(tire);

        Service service = new Service("test service", TypeOfServiceEnum.TIRECHANGE);
        service.setPrice(BigDecimal.ONE);
        entityManager.persist(service);

        OrderItem orderItem1 = new OrderItem(tire, 5L);
        OrderItem orderItem2 = new OrderItem(service, 4L);
        entityManager.persist(orderItem1);
        entityManager.persist(orderItem2);

        List<OrderItem> orderList = new ArrayList<>();
        orderList.add(orderItem1);
        orderList.add(orderItem2);

        Customer customer = new Customer("test", "test", "test", "test", "test", "test", "test", "test");
        Customer customer2 = new Customer("blah", "blah", "blah", "blah", "blah", "blah", "blah", "blah");
        Customer customer3 = new Customer("meh", "meh", "meh", "meh", "meh", "meh", "meh", "meh");
        entityManager.persist(customer);
        entityManager.persist(customer2);
        entityManager.persist(customer3);
        
        order1 = new Order(date, customer, orderList);
        order2 = new Order(date, customer2, orderList);
        order3 = new Order(date, customer3, orderList);

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testCreateNull() {
        odi.create(null);
    }

    @Test
    public void testCreate() {
        odi.create(order1);
    }

    @Test
    public void getByIdZero() {
        assertEquals(odi.getById(new Long(0)), null);
    }

    @Test
    public void getById() {
        odi.create(order1);
        Order o2 = odi.getById(new Long(order1.getId()));

        assertEquals(order1.equals(o2), true);
    }

    @Test
    public void getAll() {

        odi.create(order1);
        odi.create(order2);
        odi.create(order3);

        List<Order> orders = odi.getAll();
        assertEquals(orders.size(), 3);
        
        assertEquals(orders.contains(order1), true);
        assertEquals(orders.contains(order2), true);
        assertEquals(orders.contains(order3), true);
    }

    @Test
    public void update() {
        
        Order o1 = order1;
        odi.create(o1);
        o1.setCustomer(new Customer("blah", "blah", "blah", "blah", "blah", "blah", "blah", "blah"));
        odi.update(o1);

        assertEquals(odi.getById(order1.getId()).equals(order1), true);
        
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void updateNull() {
        odi.update(null);
    }

    @Test
    public void delete() {
        odi.create(order1);
        odi.delete(order1);
        assertEquals(odi.getAll().size(),0);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void deleteNull() {
        odi.delete(null);
    }

}
