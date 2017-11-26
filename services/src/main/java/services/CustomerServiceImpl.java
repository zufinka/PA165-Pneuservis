package services;

import cz.muni.fi.pa165.pneuservis.backend.dao.CustomerDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;
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
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> findByName(String name, String surname) {
        return customerDao.findByName(name, surname);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    @Override
    public Customer findByPhone(String phoneNumber) {
        return customerDao.findByPhone(phoneNumber);
    }

    @Override
    public List<Customer> findByAddress(String city, String street, String country, String zipCode) {
        return customerDao.findByAddress(city, street, country, zipCode);
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
        customerDao.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerDao.deleteCustomer(customer);
    }
}
