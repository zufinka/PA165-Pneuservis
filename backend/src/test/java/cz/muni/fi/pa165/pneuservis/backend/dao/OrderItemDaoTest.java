package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.PersistenceApplicationContext;
import cz.muni.fi.pa165.pneuservis.backend.dao.OrderItemDao;
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


@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class OrderItemDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    OrderItemDao odi;
    
    @PersistenceContext
    EntityManager entityManager;
    
    OrderItem o1;
    OrderItem o2;
    
    @BeforeMethod
    public void prepareOrders() {
        
        TireManufacturer tm = new TireManufacturer("Continental");
        TireProperties tp = new TireProperties(VehicleTypeEnum.P, 165, 95, 16, 95, SpeedClassEnum.H, SeasonEnum.WINTER);
        Tire tire = new Tire("WXR", tm, tp, 10, new BigDecimal(10.568));
        entityManager.persist(tm);
        entityManager.persist(tp);
        entityManager.persist(tire);

        Service service = new Service("test service", TypeOfServiceEnum.TIRECHANGE);
        service.setPrice(BigDecimal.ONE);
        entityManager.persist(service);

        o1 = new OrderItem(tire, 5L);
        o2 = new OrderItem(service, 4L);


    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testCreateNull(){
        odi.create(null);
    }
    
    public void getByIdZero(){
        assertEquals(odi.getById(new Long(0)),null);
    }
    
    @Test
    public void getById(){
        odi.create(o1);
        OrderItem o2 = odi.getById(o1.getId());
        assertEquals(o1.equals(o2), true);
    }
    
    @Test
    public void getAll(){
        
        odi.create(o1);
        odi.create(o2);
        
        List<OrderItem> orders = odi.getAll();
        assertEquals(orders.size(), 2);
        
        assertEquals(orders.contains(o1), true);
        assertEquals(orders.contains(o2), true);
    }
    
    @Test
    public void update(){
        OrderItem order1 = o1;

        odi.create(order1);
        order1.setQuantity(18L);
        odi.update(o1);

        assertEquals(odi.getById(order1.getId()).equals(order1), true);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void updateNull(){
        odi.update(null);
    }

    @Test
    public void delete(){

        odi.create(o1);
        odi.delete(o1);

        assertEquals(odi.getAll().size(), 0);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void deleteNull(){
        odi.delete(null);
    }
   
    
}
