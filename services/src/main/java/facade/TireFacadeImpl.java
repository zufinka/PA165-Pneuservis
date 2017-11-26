package facade;

import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import dto.TireDTO;
import dto.TireManufacturerDTO;
import dto.TirePropertiesDTO;
import org.springframework.stereotype.Service;
import services.MappingService;
import services.TireService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

@Service
@Transactional
public class TireFacadeImpl implements TireFacade {

    @Inject
    private TireService tireService;

    @Inject
    private MappingService mappingService;


    @Override
    public List<TireDTO> getAllTires() {
        List<Tire> l = tireService.getAllTires();
        List<TireDTO> l2 = mappingService.mapTo(l, TireDTO.class); //TODO odstranit zle mapovanie
        return l2;
    }

    @Override
    public Set<TireManufacturerDTO> getAllTireManufacturers() {
        List<TireManufacturer> l = new ArrayList<>(tireService.getAllTireManufacturers());
        return new HashSet<>(mappingService.mapTo(l, TireManufacturerDTO.class));
    }

    @Override
    public Set<TirePropertiesDTO> getAllTireProperties() {
        List<TireProperties> l = new ArrayList<>(tireService.getAllTireProperties());
        return new HashSet<>(mappingService.mapTo(l, TirePropertiesDTO.class));
    }

    @Override
    public boolean purchaseTire(Long tireID, int noPneus) {
        return tireService.purchaseTire(tireID, noPneus);
    }

    @Override
    public List<TireDTO> findByProperties(TireManufacturerDTO manufacturer, TirePropertiesDTO tireProperties) {
        TireManufacturer tm = mappingService.mapTo(manufacturer, TireManufacturer.class); //TODO zle mapovanie
        return mappingService.mapTo(tireService.findByProperties(tm, tireProperties), TireDTO.class);
    }
}
