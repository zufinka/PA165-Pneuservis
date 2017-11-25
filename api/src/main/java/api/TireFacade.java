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
     * Self explanatory
     * @return
     */
    List<TireDTO> getAllTires();

    /**
     * Self explanatory
     * @return
     */
    Set<TireManufacturerDTO> getAllTireManufacturers();

    /**
     * Self explanatory
     * @return
     */
    Set<TirePropertiesDTO> getAllTireProperties();

    /**
     * Self explanatory
     * @param tireID
     * @param noPneus
     * @return
     */
    //TODO Create class for return type in next iteration
    boolean purchaseTire(Long tireID, int noPneus);

    /**
     * Self explanatory
     * @param manufacturer
     * @param tireProperties
     * @return
     */
    List<TireDTO> findTireByProperties(TireManufacturerDTO manufacturer, TirePropertiesDTO tireProperties);

}
