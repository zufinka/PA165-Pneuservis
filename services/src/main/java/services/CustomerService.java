package services;

import cz.muni.fi.pa165.pneuservis.backend.entity.Customer;

import java.util.List;

/**
 * Interface that specify service methods for access to {@link Customer} entity
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */
public interface CustomerService {
    /**
     * Find Customer according to given ID.
     * @param id
     * @return Founded Customer or null
     * @throws IllegalArgumentException if given id is null
     * @throws exceptions.NoSuchObjectInDatabaseException if there was no customer with this ID in the database.
     */
    Customer findById(Long id);

    /**
     * Find Customer according to given name.
     * @param name First name
     * @param surname
     * @return Set of founded Customers with given name
     * @throws IllegalArgumentException if given name or surname is null
     * @throws exceptions.NoSuchObjectInDatabaseException if there was no customer with this name in the database.
     */
    List<Customer> findByName(String name, String surname);

    /**
     * Find Customer according to given email.
     * @param email
     * @return Customer with given email
     * @throws IllegalArgumentException if given email is null
     * @throws exceptions.NoSuchObjectInDatabaseException if there was no customer with this email in the database.
     */
    Customer findByEmail(String email);

    /**
     * Find Customer according to given telephone number.
     * @param phoneNumber
     * @return Customer with given telephone number
     * @throws IllegalArgumentException if given telephone number is null
     * @throws exceptions.NoSuchObjectInDatabaseException if there was no customer with this number in the database.
     */
    Customer findByPhone(String phoneNumber);

    /**
     * Find Customer according to given address.
     * @param city
     * @param street
     * @param zipCode
     * @param country
     * @return Set of founded customers with given address
     * @throws IllegalArgumentException if given city, street or psč is null
     * @throws exceptions.NoSuchObjectInDatabaseException if there was no customer with this
     *         address in the database.
     */
    List<Customer> findByAddress(String city, String street, String country, String zipCode);

    /**
     * Find all customers in the database.
     * @return Set of all customers in the database.
     */
    List<Customer>findAll();

    /**
     * Insert customer to the database.
     * @param customer to be inserted to the database
     * @throws IllegalArgumentException if given customer is null
     * @throws javax.persistence.EntityExistsException if given customer already exists in the database
     */
    void createCustomer(Customer customer);

    /**
     * Update information about customer that already is in the database.
     * @param customer to be updated
     * @throws IllegalArgumentException if the customer is null or is not present in the database
     */
    void updateCustomer(Customer customer);

    /**
     * Delete customer from database.
     * @param customer to be deleted
     * @throws IllegalArgumentException if the customer is null or is not present in the database
     */
    void deleteCustomer(Customer customer);
}
