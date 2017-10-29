package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import java.util.List;

/***
 * Interface specifies DAO. When storing the Tire both Manufacturer and TireProperties
 * are stored if not already in the database. When deleting tire(s) both Manufacturer and 
 * TireProperties are kept in db. It is expected that these values will be reused 
 * and are changing rarely.
 *
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public interface TireManagerDao {
    
    /**
     * Method will store Tire to the database, iff all the variables are properly
     * initialized. If TireProperties and Manufacturer are not yet in the db
     * method will store them.
     * @param tire to be stored 
     * @return true on success
     * @throws IllegalArgumentException on tire == null
     */
    boolean createTire(Tire tire) throws IllegalArgumentException;
    
    /**
     * Return Tire with specified id if object is in database, if not method
     * returns null;
     * @param id
     * @return 
     */
    Tire findTireById(Long id);
        
    /**
     * Returns tire with same attributes stored id db. There should be at most
     * one object like that in db. If not in db returns null.
     * @param tire
     * @return
     * @throws IllegalArgumentException on rite or tire.attribute == null
     */
    Tire findTireByAttrs(Tire tire) throws IllegalArgumentException;
    
    /**
     * Method updated values of tire in db. It is expected that this object is 
     * already bind.
     * @param tire
     * @return true on success
     * @throws IllegalArgumentException on tire == null
     */
    boolean updateTire(Tire tire) throws IllegalArgumentException;
    
    /**
     * Method returns list of all tires in database;
     * @return 
     */
    List<Tire> retrieveAllTires();

    /**
     * Method deletes tires with values matching filter. If filter is null
     * nothing happens;
     * @param tire
     * @return 
     */
    boolean deleteTire(Tire tire);
    
}
