package facade;

import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;
import dto.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.CustomerService;
import services.MappingService;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Facade methods for manipulation with {@link Customer} entity
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */
@Service
@Transactional
public class CustomerFacadeImpl implements CustomerFacade{
    @Inject
    private CustomerService customerService;

    @Inject
    private MappingService mappingService;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        checkNotNull(id);
        Customer customer = customerService.findById(id);
        return mappingService.mapTo(customer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getCustomerByName(String name, String surname) {
        checkNotNull(name, surname);
        List<Customer> customers = customerService.findByName(name, surname);
        return mappingService.mapTo(customers, CustomerDTO.class);
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        checkNotNull(email);
        Customer customer = customerService.findByEmail(email);
        return mappingService.mapTo(customer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO getCustomerByPhone(String phoneNumber) {
        checkNotNull(phoneNumber);
        Customer customer = customerService.findByPhone(phoneNumber);
        return mappingService.mapTo(customer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getCustomerByAddress(String city, String street, String country, String zipCode) {
        checkNotNull(city, street, country, zipCode);
        List<Customer> customers = customerService.findByAddress(city, street, country, zipCode);
        return mappingService.mapTo(customers, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return mappingService.mapTo(customers, CustomerDTO.class);
    }

    @Override
    public void createCustomer(CustomerDTO customer) {
        checkNotNull(customer);
        Customer newCustomer = mappingService.mapTo(customer, Customer.class);
        customerService.createCustomer(newCustomer);
    }

    @Override
    public void changeCustomer(CustomerDTO customer) {
        checkNotNull(customer);
        Customer changedCustomer = mappingService.mapTo(customer, Customer.class);
        customerService.updateCustomer(changedCustomer);
    }

    @Override
    public void deleteCustomer(CustomerDTO customer) {
        checkNotNull(customer);
        Customer customerToDelete = mappingService.mapTo(customer, Customer.class);
        customerService.deleteCustomer(customerToDelete);
    }
}
