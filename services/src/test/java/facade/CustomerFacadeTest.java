package facade;

import config.ServiceConfiguration;
import config.TireDataConfig;
import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;
import dto.CustomerDTO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.CustomerService;
import services.MappingService;

import javax.inject.Inject;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/***
 * @author Róbert Ivan , 461468@mail.muni.cz
 */

@ContextConfiguration(classes = {ServiceConfiguration.class, TireDataConfig.class})
public class CustomerFacadeTest extends AbstractTestNGSpringContextTests{
    
    @Mock
    private CustomerService customerService;

    @Spy
    @Inject
    private MappingService mappingService;
    
    @InjectMocks
    CustomerFacade customerFacade = new CustomerFacadeImpl();
    
    private Customer customer1, customer2;
    
    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        createCustomers();
        
    }
    private void createCustomers(){
      customer1 = new Customer("TestName1","TestSurname1","Testcity1","Teststreet1","TestzipCode1","Testcountry1","Testemail1","TestphoneNumber1");
      customer2 = new Customer("TestName2","TestSurname2","Testcity2","Teststreet2","TestzipCode2","Testcountry2","Testemail2","TestphoneNumber2");
          
    }
    
     
    @Test
    public void createCustomerTest() {
        CustomerDTO customerDTO = mappingService.mapTo(customer1, CustomerDTO.class);
        customerFacade.createCustomer(customerDTO);
        verify(customerService).createCustomer(customer1);
    }
    
    @Test
    public void updateCustomerTest() {
        CustomerDTO customerDTO = mappingService.mapTo(customer1, CustomerDTO.class);
        customerFacade.changeCustomer(customerDTO);
        verify(customerService).updateCustomer(customer1);
    }
    
    @Test
    public void deleteCustomerTest() {
        CustomerDTO customerDTO = mappingService.mapTo(customer1, CustomerDTO.class);
        customerFacade.deleteCustomer(customerDTO);
        verify(customerService).deleteCustomer(customer1);
    }
    @Test
    public void getCustomerTest() {
        when(customerService.findById(any(Long.class))).thenReturn(customer1);
        CustomerDTO customerDTO = mappingService.mapTo(customer1, CustomerDTO.class);
        customerFacade.getCustomerById(1L);
        verify(customerService).findById(1L);
    }
    
       
}
