package config;

import cz.muni.fi.pa165.pneuservis.backend.dao.TireManagerDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import services.TireServiceTest;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@ComponentScan(basePackageClasses = {TireServiceTest.class})
public class TestConfig {

    @Bean
    public TireManagerDao getTireManager() {

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

        Tire t2 = new Tire();
        t2.setOnStock(20);
        t2.setName("Koleso 2");
        t2.setTireManufacturer(tm2);
        t2.setTireProperties(tp2);
        t2.setPrice(new BigDecimal(20));

        Tire t3 = new Tire();
        t3.setOnStock(30);
        t3.setName("Koleso 3");
        t3.setTireManufacturer(tm3);
        t3.setTireProperties(tp1);
        t3.setPrice(new BigDecimal(30));

        Tire t4 = new Tire();
        t4.setOnStock(40);
        t4.setName("Koleso 4");
        t4.setTireManufacturer(tm1);
        t4.setTireProperties(tp2);
        t4.setPrice(new BigDecimal(40));

        Tire t5 = new Tire();
        t5.setOnStock(50);
        t5.setName("Koleso 5");
        t5.setTireManufacturer(tm2);
        t5.setTireProperties(tp1);
        t5.setPrice(new BigDecimal(50));

        ArrayList<TireManufacturer> tml = new ArrayList<>();
        tml.add(tm1);
        tml.add(tm2);
        tml.add(tm3);

        ArrayList<TireProperties> tpl = new ArrayList<>();
        tpl.add(tp1);
        tpl.add(tp2);

        ArrayList<Tire> tl = new ArrayList<>();
        tl.add(t1);
        tl.add(t2);
        tl.add(t3);
        tl.add(t4);
        tl.add(t5);

        TireManagerDao tmd;
        tmd = mock(TireManagerDao.class);
        when(tmd.retrieveAllTireManufacturers()).thenReturn(tml);
        when(tmd.retrieveAllTireProperties()).thenReturn(tpl);
        when(tmd.retrieveAllTires()).thenReturn(tl);
        when(tmd.findTiresByProperties(null, null)).thenReturn(tl);

        ArrayList<Tire> retByManuf = new ArrayList<Tire>();
        retByManuf.add(t1);
        retByManuf.add(t4);
        when(tmd.findTiresByProperties(tm1, null)).thenReturn(retByManuf);

        ArrayList<Tire> retByProps = new ArrayList<Tire>();
        retByProps.add(t1);
        retByProps.add(t3);
        retByProps.add(t5);
        when(tmd.findTiresByProperties(null, tp1)).thenReturn(retByProps);

        ArrayList<Tire> retByBoth = new ArrayList<>();
        retByBoth.add(t1);
        when(tmd.findTiresByProperties(tm1, tp1)).thenReturn(retByBoth);

        ArrayList<Tire> retByBoth2 = new ArrayList<>();
        retByBoth.add(t4);
        when(tmd.findTiresByProperties(tm1, tp2)).thenReturn(retByBoth);

        when(tmd.updateTire(t1)).thenReturn(true);
        when(tmd.findTireById(new Long(1))).thenReturn(t1);
        return tmd;
    }


}
