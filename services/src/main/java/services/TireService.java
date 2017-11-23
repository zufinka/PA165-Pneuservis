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

    List<Tire> getAllTires();
    Set<TireManufacturer> getAllTireManufacturers();
    Set<TireProperties> getAllTireProperties();
    boolean purchaseTire(Long tireID, int noPneus);
    List<Tire> findByProperties(TireManufacturer manufacturer, TirePropertiesDTO tireProperties);

}
