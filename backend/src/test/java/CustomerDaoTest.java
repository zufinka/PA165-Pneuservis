
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
    
    private Customer custumer1;
    private Customer custumer2;
    private Customer custumer3;
    
    @BeforeMethod
    public void prepareCustomers() {
        custumer1 = new Customer("TestName1","TestSurname1","Testcity1","Teststreet1","TestzipCode1","Testcountry1","Testemail1","TestphoneNumber1");
        custumer2 = new Customer("TestName2","TestSurname2","Testcity2","Teststreet2","TestzipCode2","Testcountry2","Testemail2","TestphoneNumber2");
        custumer3 = new Customer("TestName3","TestSurname3","Testcity3","Teststreet3","TestzipCode3","Testcountry3","Testemail3","TestphoneNumber3");
    }
    
    @Test
    public void testCreateCustomer() {
        Assert.assertEquals(customerManagerDao.findAll().size(), 0);
        customerManagerDao.createCustomer(custumer1);
        Assert.assertEquals(customerManagerDao.findAll().size(), 1);
        Assert.assertTrue(customerManagerDao.findAll().get(0).equals(custumer1));
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testCreateNullCustomer() {
        customerManagerDao.createCustomer(null);
    }
    
    @Test
    public void testUpdateCustomer() {
        customerManagerDao.createCustomer(custumer1);
        Assert.assertEquals(customerManagerDao.findAll().get(0).getName(), "TestName");
        custumer1.setName("Marek");
        customerManagerDao.updateCustomer(custumer1);
        Assert.assertEquals(customerManagerDao.findAll().get(0).getName(), "Marek");
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testUpdateNullCustomer() {
        customerManagerDao.updateCustomer(null);
    }
    
    @Test
    public void testDeleteCustomer() {
        customerManagerDao.createCustomer(custumer1);
        customerManagerDao.createCustomer(custumer2);
        Assert.assertEquals(customerManagerDao.findAll().size(), 2);
        customerManagerDao.deleteCustomer(custumer1);
        Assert.assertEquals(customerManagerDao.findAll().size(), 1);
        customerManagerDao.deleteCustomer(custumer2);
        Assert.assertEquals(customerManagerDao.findAll().size(), 0);
    }
    
    @Test
    public void testFindCustomereById() {
        customerManagerDao.createCustomer(custumer1);
        Assert.assertEquals(customerManagerDao.findById(custumer1.getId()), custumer1);
    }
    
    @Test
    public void testRetrieveAllOrders() {
        customerManagerDao.createCustomer(custumer1);
        customerManagerDao.createCustomer(custumer2);
        customerManagerDao.createCustomer(custumer3);
        Assert.assertEquals(customerManagerDao.findAll().size(), 3);
        Assert.assertTrue(customerManagerDao.findAll().get(0).equals(custumer1));
        Assert.assertTrue(customerManagerDao.findAll().get(1).equals(custumer2));
        Assert.assertTrue(customerManagerDao.findAll().get(2).equals(custumer3));
    }
}
