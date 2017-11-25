package api;

import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import dto.TireDTO;
import dto.TireManufacturerDTO;
import dto.TirePropertiesDTO;

import java.util.List;
import java.util.Set;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

public interface TireFacade {

    /**
     * Returns list of all tires in db
     * @return
     */
    List<TireDTO> getAllTires();

    /**
     * Returns set of all tire manufacturers
     * @return
     */
    Set<TireManufacturerDTO> getAllTireManufacturers();

     /**
     * Returns set of all existing tire properties in db
     * @return
     */
    Set<TirePropertiesDTO> getAllTireProperties();

    /**
     * If there are enough pieces on stock of tire T specified by id. Method will decrease the availible
     * number of T by noPneus.
     * @param tireID
     * @param noPneus
     * @return
     */
    //TODO Create class for return type in next iteration
    boolean purchaseTire(Long tireID, int noPneus);

    /**
     * Method find all tires matching values in manufacturer and tireProperties
     * @param manufacturer has to be class with valid ID
     * @param tireProperties
     * @return
     */
    List<TireDTO> findTireByProperties(TireManufacturerDTO manufacturer, TirePropertiesDTO tireProperties);

}
