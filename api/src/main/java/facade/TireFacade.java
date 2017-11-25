package facade;

import dto.TireDTO;
import dto.TireManufacturerDTO;
import dto.TirePropertiesDTO;

import java.util.List;
import java.util.Set;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

public interface TireFacade {

    List<TireDTO> getAllTires();

    Set<TireManufacturerDTO> getAllTireManufacturers();

    Set<TirePropertiesDTO> getAllTireProperties();

    //Tu by to chcelo nejaky normalnu navratovu spravu
    boolean purchaseTire(Long tireID, int noPneus);

    List<TireDTO> findByProperties(TireManufacturerDTO manufacturer, TirePropertiesDTO tireProperties);

}
