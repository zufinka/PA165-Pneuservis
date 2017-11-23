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

        createDummyData();

        TireDataCache tdc = new TireDataCache();
        tdc.setAllTireManufacturers(new HashSet<>(tireManagerDao.retrieveAllTireManufacturers()));
        tdc.setAllTireProperties(new HashSet<>(tireManagerDao.retrieveAllTireProperties()));
        return tdc;
    }

    void createDummyData(){
        TireManufacturer tm1 = new TireManufacturer("Kolesovyrobadopici");
        TireManufacturer tm2 = new TireManufacturer("Kokotnekolesa");
        TireManufacturer tm3 = new TireManufacturer("Tociacesakokoty");

        TireProperties tp1 = new TireProperties();
        tp1.setAspectRatio(32);
        tp1.setDiameter(43);
        tp1.setLoadIndex(12);
        tp1.setSeason(SeasonEnum.SUMMER);
        tp1.setSpeedClass(SpeedClassEnum.C);
        tp1.setWidth(14);
        tp1.setVehicleType(VehicleTypeEnum.T);

        TireProperties tp2 = new TireProperties();
        tp2.setAspectRatio(32);
        tp2.setDiameter(43);
        tp2.setLoadIndex(12);
        tp2.setSeason(SeasonEnum.WINTER);
        tp2.setSpeedClass(SpeedClassEnum.K);
        tp2.setWidth(89);
        tp2.setVehicleType(VehicleTypeEnum.LT);

        Tire t1 = new Tire();
        t1.setOnStock(10);
        t1.setName("Koleso dopici");
        t1.setTireManufacturer(tm1);
        t1.setTireProperties(tp2);
        t1.setPrice(new BigDecimal(15));

        Tire t5 = new Tire();
        t5.setOnStock(10);
        t5.setName("Kokotne koleso");
        t5.setTireManufacturer(tm2);
        t5.setTireProperties(tp1);
        t5.setPrice(new BigDecimal(15));

        Tire t2 = new Tire();
        t2.setOnStock(10);
        t2.setName("Koleso pica");
        t2.setTireManufacturer(tm3);
        t2.setTireProperties(tp2);
        t2.setPrice(new BigDecimal(15));

        Tire t3 = new Tire();
        t3.setOnStock(10);
        t3.setName("Jebem kolesa");
        t3.setTireManufacturer(tm1);
        t3.setTireProperties(tp1);
        t3.setPrice(new BigDecimal(15));

        Tire t4 = new Tire();
        t4.setOnStock(10);
        t4.setName("Koleso Koleso");
        t4.setTireManufacturer(tm2);
        t4.setTireProperties(tp1);
        t4.setPrice(new BigDecimal(15));

        tireManagerDao.createTire(t1);
        tireManagerDao.createTire(t2);
        tireManagerDao.createTire(t3);
        tireManagerDao.createTire(t4);
        tireManagerDao.createTire(t5);
    }

}
