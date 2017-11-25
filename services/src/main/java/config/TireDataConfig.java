package config;

import cz.muni.fi.pa165.pneuservis.backend.PersistenceApplicationContext;
import cz.muni.fi.pa165.pneuservis.backend.dao.TireManagerDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.TireDataCache;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

@Configuration
@Import(PersistenceApplicationContext.class)
public class TireDataConfig {

    @Inject
    private TireManagerDao tireManagerDao;

    @Bean
    public TireDataCache createTireDataCache(){

        TireDataCache tdc = new TireDataCache();
        tdc.setAllTireManufacturers(new HashSet<>(tireManagerDao.retrieveAllTireManufacturers()));
        tdc.setAllTireProperties(new HashSet<>(tireManagerDao.retrieveAllTireProperties()));
        return tdc;

    }
}
