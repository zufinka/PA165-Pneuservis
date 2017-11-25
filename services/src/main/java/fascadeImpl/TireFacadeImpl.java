package fascadeImpl;

import api.TireFacade;
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
        return mapTireToDTO(l);
    }

    @Override
    public Set<TireManufacturerDTO> getAllTireManufacturers() {
        List<TireManufacturer> l = new ArrayList<>(tireService.getAllTireManufacturers());
        return new HashSet<>(mappingService.mapTo(l, TireManufacturerDTO.class));
    }

    @Override
    public Set<TirePropertiesDTO> getAllTireProperties() {
        List<TireProperties> l = new ArrayList<>(tireService.getAllTireProperties());
        HashSet<TirePropertiesDTO> tpd = new HashSet<>(mappingService.mapTo(l, TirePropertiesDTO.class));
        return tpd;
    }

    @Override
    public boolean purchaseTire(Long tireID, int noPneus) {
        return tireService.purchaseTire(tireID, noPneus);
    }

    @Override
    public List<TireDTO> findTireByProperties(TireManufacturerDTO manufacturer, TirePropertiesDTO tireProperties) {
        if (manufacturer == null && tireProperties == null) return getAllTires();
        TireManufacturer tm = mappingService.mapTo(manufacturer, TireManufacturer.class);
        return mapTireToDTO(tireService.findTireByProperties(tm, tireProperties));
    }

    //Aux
    private List<TireDTO> mapTireToDTO(List<Tire> tires){
        List<TireDTO> l2 = new ArrayList<>();
        for(Tire t: tires){
            TireDTO dto = mappingService.mapTo(t, TireDTO.class);
            dto.setTirePropertiesDTO(mappingService.mapTo(t.getTireProperties(), TirePropertiesDTO.class));
            l2.add(dto);
        }
        return l2;
    }
}
