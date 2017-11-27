package services;

import config.ServiceConfiguration;
import config.TireDataConfig;
import cz.muni.fi.pa165.pneuservis.backend.dao.CustomerDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;


/***
 * @author Róbert Ivan , 461468@mail.muni.cz
 */
@ContextConfiguration(classes = {ServiceConfiguration.class, TireDataConfig.class})
public class CustomerServiceTest extends AbstractTestNGSpringContextTests {
    
    
    @Mock
    private CustomerDao customerDao;
    
    @Inject
    @InjectMocks
    private CustomerService customerService;
    
    private Customer customer1, customer2;
    
   @BeforeMethod
   public void Init(){
       MockitoAnnotations.initMocks(this);
       createCustomers();
       
        when(customerDao.findById(1L)).thenReturn(customer1);
        when(customerDao.findById(2L)).thenReturn(customer2);
        when(customerDao.findById(null)).thenThrow(NullPointerException.class);
        when(customerDao.findAll()).thenReturn(Arrays.asList(customer1, customer2));
        
   }
   
    private void createCustomers(){
      customer1 = new Customer("TestName1","TestSurname1","Testcity1","Teststreet1","TestzipCode1","Testcountry1","Testemail1","TestphoneNumber1");
      customer2 = new Customer("TestName2","TestSurname2","Testcity2","Teststreet2","TestzipCode2","Testcountry2","Testemail2","TestphoneNumber2");          

      
    }
    
   @Test(expectedExceptions = NullPointerException.class)
    public void createNullCustomerTest() {
        customerService.createCustomer(null);
    }

    
    @Test(expectedExceptions = NullPointerException.class)
    public void updateNullCustomerTest() {
        customerService.updateCustomer(null);
    }

    
    
    @Test
    public void createCustomerTest() {
        customerService.createCustomer(customer1);        
        Collection<Customer> customers = customerService.findAll();
        assertEquals(1, customers.size());
        
    }
    @Test
    public void updateCustomerTest() {    
        customerService.createCustomer(customer2);
        customer2.setCity("new City");
        customerService.updateCustomer(customer2);
        assertEquals(customer2.getCity(), "new City");
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void deleteNullCustomerTest() {
        customerService.deleteCustomer(null);
    }
      
    @Test
    public void deleteCustomerTest() {
        Collection<Customer> customers = customerService.findAll();
        customerService.deleteCustomer(customer1);
        customers = customerService.findAll();
        assertEquals(1, customers.size());
    }
    
    @Test
    public void getAllCustomersTest(){
        Collection<Customer> customers = customerService.findAll();
        assertEquals(1, customers.size());
    }
    

}
