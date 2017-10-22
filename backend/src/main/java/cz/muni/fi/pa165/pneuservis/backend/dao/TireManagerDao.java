package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.tire.Manufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.tire.Manufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.tire.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.tire.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.tire.TireProperties;
import cz.muni.fi.pa165.pneuservis.backend.entity.tire.TireProperties;
import java.util.List;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 * 
 * Interface specifies DAO. When storing the Tire both Manufacturer and TireProperties
 * are stored if not already in the database. When deleting tire(s) both Manufacturer and 
 * TireProperties are kept in db. It is expected that these values will be reused 
 * and are changing rarely.
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
     * Method updates properties of tire old with values of data. Old is to be 
     * previously stored in db. If old is not found in database method will 
     * returns false. 
     * @param old
     * @param data
     * @return true on success
     * @throws IllegalArgumentException on any param == null
     */
    boolean updateTire(Tire old, Tire data) throws IllegalArgumentException;
    
    /**
     * Method returns list of all tires in database;
     * @return 
     */
    List<Tire> retrieveAllTires();
    
    /**
     * Method returns list of all tires in database that match the the values 
     * specified in filter. If filter is null method will return all Tires
     * @param filter
     * @return true on success
     */
    List<Tire> getTiresByFilter(Tire filter);
    
    /**
     * Method deletes tires with values matching filter. If filter is null
     * nothing happens;
     * @param tire
     * @return 
     */
    boolean deleteTire(Tire filter);
    
    /**
     * Method deletes all tires with specified manufacturer and the manufacturer. 
     * If param is null nothing happens.
     * @param manufacturer
     * @return true on success
     */
    boolean deleteCategory(Manufacturer manufacturer);
    
    /**
     * Method deletes all tires with specified tire properties and the TirePropery.
     * If param is null nothing happens.
     * @param propetries
     * @return true on success
     */
    boolean deleteCategory(TireProperties propetries);
}
