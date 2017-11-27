package services;

import cz.muni.fi.pa165.pneuservis.backend.dao.CustomerDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;
import exceptions.NoSuchObjectInDatabaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of service methods for access to {@link Customer} entity
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Inject
    CustomerDao customerDao;

    @Override
    public Customer findById(Long id) {
        Customer foundCustomer = customerDao.findById(id);
        if (foundCustomer == null){
            throw new NoSuchObjectInDatabaseException("No customer with this id in database.");
        }
        return foundCustomer;
    }

    @Override
    public List<Customer> findByName(String name, String surname) {
        List<Customer> customers = customerDao.findByName(name, surname);
        if (customers == null){
            throw new NoSuchObjectInDatabaseException("No customer with this name and surname in database.");
        }
        return customers;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer foundCustomer = customerDao.findByEmail(email);
        if (foundCustomer == null){
            throw new NoSuchObjectInDatabaseException("No customer with this email in database.");
        }
        return foundCustomer;
    }

    @Override
    public Customer findByPhone(String phoneNumber) {
        Customer foundCustomer = customerDao.findByPhone(phoneNumber);
        if (foundCustomer == null){
            throw new NoSuchObjectInDatabaseException("No customer with this phoneNumber in database.");
        }
        return foundCustomer;
    }

    @Override
    public List<Customer> findByAddress(String city, String street, String country, String zipCode) {
        List<Customer> customers = customerDao.findByAddress(city, street, country, zipCode);
        if (customers == null){
            throw new NoSuchObjectInDatabaseException("No customer with this address in database.");
        }
        return customers;
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public void createCustomer(Customer customer) {
        customerDao.createCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            customerDao.updateCustomer(customer);
        }catch (IllegalArgumentException e){
            throw new NoSuchObjectInDatabaseException("This customer is not in database.");
        }

    }

    @Override
    public void deleteCustomer(Customer customer) {
        try {
            customerDao.deleteCustomer(customer);
        }catch (IllegalArgumentException e){
            throw new NoSuchObjectInDatabaseException("This customer in not database.");
        }

    }
}
