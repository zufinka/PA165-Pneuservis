
import cz.muni.fi.pa165.pneuservis.backend.PersistenceApplicationContext;
import javax.inject.Inject;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import cz.muni.fi.pa165.pneuservis.backend.dao.CustomerDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



/***
 * @author Róbert Ivan , 461468@mail.muni.cz
 */


@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CustomerDaoTest extends AbstractTestNGSpringContextTests{
    
  @Inject  
  private CustomerDao customerManagerDao;
  
    @PersistenceContext
    private EntityManager entityManager;
    
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    
    @BeforeMethod
    public void prepareCustomers() {
        customer1 = new Customer("TestName1","TestSurname1","Testcity1","Teststreet1","TestzipCode1","Testcountry1","Testemail1","TestphoneNumber1");
        customer2 = new Customer("TestName2","TestSurname2","Testcity2","Teststreet2","TestzipCode2","Testcountry2","Testemail2","TestphoneNumber2");
        customer3 = new Customer("TestName3","TestSurname3","Testcity3","Teststreet3","TestzipCode3","Testcountry3","Testemail3","TestphoneNumber3");
    }
    
    @Test
    public void testCreateCustomer() {
        Assert.assertEquals(customerManagerDao.findAll().size(), 0);
        customerManagerDao.createCustomer(customer1);
        Assert.assertEquals(customerManagerDao.findAll().size(), 1);
        Assert.assertTrue(customerManagerDao.findAll().get(0).equals(customer1));
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testCreateNullCustomer() {
        customerManagerDao.createCustomer(null);
    }
    
    @Test
    public void testUpdateCustomer() {
        customerManagerDao.createCustomer(customer1);
        Assert.assertEquals(customerManagerDao.findAll().get(0).getName(), "TestName1");
        customer1.setName("Marek");
        customerManagerDao.updateCustomer(customer1);
        Assert.assertEquals(customerManagerDao.findAll().get(0).getName(), "Marek");
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testUpdateNullCustomer() {
        customerManagerDao.updateCustomer(null);
    }
    
    @Test
    public void testDeleteCustomer() {
        customerManagerDao.createCustomer(customer1);
        customerManagerDao.createCustomer(customer2);
        Assert.assertEquals(customerManagerDao.findAll().size(), 2);
        customerManagerDao.deleteCustomer(customer1);
        Assert.assertEquals(customerManagerDao.findAll().size(), 1);
        customerManagerDao.deleteCustomer(customer2);
        Assert.assertEquals(customerManagerDao.findAll().size(), 0);
    }
    
    @Test
    public void testFindCustomereById() {
        customerManagerDao.createCustomer(customer1);
        Assert.assertEquals(customerManagerDao.findById(customer1.getId()), customer1);
    }
    
    @Test
    public void testRetrieveAllOrders() {
        customerManagerDao.createCustomer(customer1);
        customerManagerDao.createCustomer(customer2);
        customerManagerDao.createCustomer(customer3);
        Assert.assertEquals(customerManagerDao.findAll().size(), 3);
        Assert.assertTrue(customerManagerDao.findAll().get(0).equals(customer1));
        Assert.assertTrue(customerManagerDao.findAll().get(1).equals(customer2));
        Assert.assertTrue(customerManagerDao.findAll().get(2).equals(customer3));
    }
}
