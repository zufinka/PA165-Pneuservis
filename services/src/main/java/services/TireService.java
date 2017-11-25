package services;

import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
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
public interface TireService {

    /**
     * Self explanatory
     * @return
     */
    List<Tire> getAllTires();

    /**
     * Self explanatory
     * @return
     */
    Set<TireManufacturer> getAllTireManufacturers();

    /**
     * Self explanatory
     * @return
     */
    Set<TireProperties> getAllTireProperties();

    /**
     * Self explanatory
     * @param tireID
     * @param noPneus
     * @return
     */
    boolean purchaseTire(Long tireID, int noPneus);

    /**
     * Self explanatory
     * @param manufacturer
     * @param tireProperties
     * @return
     */
    List<Tire> findTireByProperties(TireManufacturer manufacturer, TirePropertiesDTO tireProperties);

}
