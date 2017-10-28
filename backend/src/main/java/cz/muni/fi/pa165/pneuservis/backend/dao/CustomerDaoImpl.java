package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */
public class CustomerDaoImpl implements CustomerDao{
    private EntityManager em;

    @Override
    public Customer findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("ID is null.");
        }
        return em.find(Customer.class, id);
    }

    //TODO
    @Override
    public List<Customer> findByName(String name, String surname) {
        if (name == null || surname == null){
            throw new IllegalArgumentException("Fist name or surname is null.");
        }
        return em.createQuery("SELECT c FROM CustomerTable c where c.name =");
    }

    //TODO
    @Override
    public Customer findByEmail(String email) {
        if (email == null){
            throw new IllegalArgumentException("Email is null.");
        }
        return em.find(Customer.class, email);
    }

    //TODO
    @Override
    public Customer findByPhone(Integer phoneNumber) {
        if (phoneNumber == null){
            throw new IllegalArgumentException("Telephone number is null.");
        }
        return em.find(Customer.class, phoneNumber);
    }

    //TODO
    @Override
    public List<Customer> findByAddress(String city, String street, Integer psc) {
       return null;
    }

    @Override
    public List<Customer> findAll() {
        return em.createQuery("SELECT c from CustomerTable c", Customer.class).getResultList();
    }

    //TODO
    @Override
    public void createCustomer(Customer customer) {

    }

    //TODO
    @Override
    public void updateCustomer(Customer customer) {

    }

    //TODO
    @Override
    public Customer deleteCustomer(Customer customer) {
        return null;
    }
}
