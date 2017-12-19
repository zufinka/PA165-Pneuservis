package config;

import cz.muni.fi.pa165.pneuservis.backend.PersistenceApplicationContext;
import cz.muni.fi.pa165.pneuservis.backend.dao.TireManagerDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import facade.FacadePackageMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.ServicePackageMarker;
import services.TireDataCache;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {ServicePackageMarker.class})
public class TireDataConfig {

    @Inject
    private TireManagerDao tireManagerDao;

    @Bean
    public TireDataCache createTireDataCache(){

        TireDataCache tdc = new TireDataCache();
        createDummyData();
        tdc.setAllTireManufacturers(new HashSet<>(tireManagerDao.retrieveAllTireManufacturers()));
        tdc.setAllTireProperties(new HashSet<>(tireManagerDao.retrieveAllTireProperties()));
        return tdc;

    }

    public void createDummyData(){

        TireManufacturer tm1 = new TireManufacturer("Manuf 1");
        TireManufacturer tm2 = new TireManufacturer("Manuf 2");
        TireManufacturer tm3 = new TireManufacturer("Manuf 3");

        TireProperties tp1 = new TireProperties();
        tp1.setAspectRatio(10);
        tp1.setDiameter(10);
        tp1.setLoadIndex(10);
        tp1.setSeason(SeasonEnum.SUMMER);
        tp1.setSpeedClass(SpeedClassEnum.B);
        tp1.setWidth(10);
        tp1.setVehicleType(VehicleTypeEnum.T);

        TireProperties tp2 = new TireProperties();
        tp2.setAspectRatio(20);
        tp2.setDiameter(20);
        tp2.setLoadIndex(20);
        tp2.setSeason(SeasonEnum.WINTER);
        tp2.setSpeedClass(SpeedClassEnum.C);
        tp2.setWidth(20);
        tp2.setVehicleType(VehicleTypeEnum.P);

        Tire t1 = new Tire();
        t1.setOnStock(10);
        t1.setName("Koleso 1");
        t1.setTireManufacturer(tm1);
        t1.setTireProperties(tp1);
        t1.setPrice(new BigDecimal(3));
        t1.setImageUrl("https://i5.walmartimages.com/dfw/4ff9c6c9-733e/k2-_395dec54-c054-45c9-afc1-4e0eaad320b1.v1.jpg");
        t1.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.");

        Tire t2 = new Tire();
        t2.setOnStock(20);
        t2.setName("Koleso 2");
        t2.setTireManufacturer(tm2);
        t2.setTireProperties(tp2);
        t2.setPrice(new BigDecimal(20));
        t2.setImageUrl("https://i5.walmartimages.com/dfw/4ff9c6c9-733e/k2-_395dec54-c054-45c9-afc1-4e0eaad320b1.v1.jpg");
        t2.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.");

        
        Tire t3 = new Tire();
        t3.setOnStock(30);
        t3.setName("Koleso 3");
        t3.setTireManufacturer(tm3);
        t3.setTireProperties(tp1);
        t3.setPrice(new BigDecimal(30));
        t3.setImageUrl("https://i5.walmartimages.com/dfw/4ff9c6c9-733e/k2-_395dec54-c054-45c9-afc1-4e0eaad320b1.v1.jpg");
        t3.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.");

        
        Tire t4 = new Tire();
        t4.setOnStock(40);
        t4.setName("Koleso 4");
        t4.setTireManufacturer(tm1);
        t4.setTireProperties(tp2);
        t4.setPrice(new BigDecimal(40));
        t4.setImageUrl("https://i5.walmartimages.com/dfw/4ff9c6c9-733e/k2-_395dec54-c054-45c9-afc1-4e0eaad320b1.v1.jpg");
        t4.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.");

        
        Tire t5 = new Tire();
        t5.setOnStock(50);
        t5.setName("Koleso 5");
        t5.setTireManufacturer(tm2);
        t5.setTireProperties(tp1);
        t5.setPrice(new BigDecimal(50));
        t5.setImageUrl("https://i5.walmartimages.com/dfw/4ff9c6c9-733e/k2-_395dec54-c054-45c9-afc1-4e0eaad320b1.v1.jpg");
        t5.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.");

        
        tireManagerDao.createTire(t1);
        tireManagerDao.createTire(t2);
        tireManagerDao.createTire(t3);
        tireManagerDao.createTire(t4);
        tireManagerDao.createTire(t5);

    }

}
