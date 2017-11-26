package services;

import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import dto.TireDTO;
import dto.TireManufacturerDTO;
import dto.TirePropertiesDTO;
import exceptions.NoSuchObjectInDatabaseException;

import java.util.List;
import java.util.Set;


/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public interface TireService {

    /**
     * Returns list of all tires stored in db
     * @return
     */
    List<Tire> getAllTires();

    /**
     * Returns set of all tire manufacturers
     * @return
     */
    Set<TireManufacturer> getAllTireManufacturers();

    /**
     * Returns set of all existing tire properties in db
     * @return
     */
    Set<TireProperties> getAllTireProperties();

    /**
     * If there are enough pieces on stock of tire T specified by id. Method will decrease the availible
     * number of T by noPneus.
     * @param tireID
     * @param noPneus
     * @return
     */
    boolean purchaseTire(Long tireID, int noPneus) throws NoSuchObjectInDatabaseException;

    /**
     * Method find all tires matching values in manufacturer and tireProperties
     * @param manufacturer has to be class with valid ID
     * @param tireProperties
     * @return
     */
    List<Tire> findTireByProperties(TireManufacturer manufacturer, List<TireProperties> tireProperties);

}
