package services;

import cz.muni.fi.pa165.pneuservis.backend.dao.TireManagerDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import dto.TireManufacturerDTO;
import dto.TirePropertiesDTO;
import exceptions.NoSuchObjectInDatabaseException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

/**
 * *
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
@Service
@Transactional
public class TireServiceImpl implements TireService {

    @Inject
    private TireDataCache tdc;

    @Inject
    private TireManagerDao tireManagerDao;

    @Override
    public List<Tire> getAllTires() {
        return tireManagerDao.retrieveAllTires();
    }

    @Override
    public Set<TireManufacturer> getAllTireManufacturers() {
        return Collections.unmodifiableSet(tdc.getAllTireManufacturers());
    }

    @Override
    public Set<TireProperties> getAllTireProperties() {
        return Collections.unmodifiableSet(tdc.getAllTireProperties());
    }

    @Override
    public boolean purchaseTire(Long tireID, int noPneus) throws NoSuchObjectInDatabaseException {
        if (noPneus <= 0) {
            throw new IllegalArgumentException("number of tires is negative or zero");
        }
        if (tireID == null) {
            throw new IllegalArgumentException("Id is null");
        }
        if (tireID <= 0) {
            throw new IllegalArgumentException("negative id");
        }

        Tire t = tireManagerDao.findTireById(tireID);
        if (t == null) {
            throw new NoSuchObjectInDatabaseException("object with id " + tireID + " not found");
        }
        if (t.getOnStock() <= noPneus) {
            return false; //Insufficient number of pneus on stock
        }
        t.setOnStock(t.getOnStock() - noPneus);
        tireManagerDao.updateTire(t);

        return true;
    }

    @Override
    public List<Tire> findTireByProperties(TireManufacturer manufacturer, List<TireProperties> tireProperties) {

        Set<Tire> set = new HashSet<>();
        if (tireProperties != null) {
            for (TireProperties tp : tireProperties) {
                set.addAll(tireManagerDao.findTiresByProperties(manufacturer, tp));
            }
            return Collections.unmodifiableList(new ArrayList<>(set));
        } else {
            return Collections.unmodifiableList(tireManagerDao.findTiresByProperties(manufacturer, null));
        }

    }

    @Override
    public Tire getById(Long id) {
        return tireManagerDao.findTireById(id);
    }

}
