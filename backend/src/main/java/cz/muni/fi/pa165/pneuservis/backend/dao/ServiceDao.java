package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.enums.typeOfServiceEnum;
import java.math.BigDecimal;
import java.util.List;


/***
 * Interface that specify DAO for entity Service {@link Service}
 * @author Róbert Ivan , 461468@mail.muni.cz
 */

public interface ServiceDao {
    
    /**
     * Method will store Service to the database, if all the variables are properly
     * initialized.
     * @param service to be stored 
     */
    void createService(Service service) throws IllegalArgumentException;
    
    /**
     * Return Service with specified id if object is in database.
     * returns null;
     * @param id
     * @return 
     */
    Service findServiceById(Long id);
        
    /**
     * Returns service with same names stored id db.
     * @param name
     * @return
     */
    Service findByName(String name);
    /**
     * Returns service with same price stored id db
     * @param price
     * @return
     */
    List<Service> findByPrice(BigDecimal price);
    /**
     * Returns service with same type of service stored id db
     * @param serviceType
     * @return
     */
    Service findByTypeofService(typeOfServiceEnum serviceType);
    
    /**
     * Method updated values of service in db. It is expected that this object is 
     * already bind.
     * @param service
     */
    
    
    void updateService(Service service) throws IllegalArgumentException;
    
    /**
     * Method returns list of all services in database;
     * @return 
     */
    
    List<Service> retrieveAllServices();
    
    /**
     * Method deletes service with values matching filter. If filter is null
     * nothing happens;
     * @param service
     */
    void deleteService(Service service);
}
