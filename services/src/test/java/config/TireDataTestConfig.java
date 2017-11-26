package config;

import cz.muni.fi.pa165.pneuservis.backend.dao.TireManagerDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.TireDataCache;
import services.TireServiceTest;

import javax.inject.Inject;
import java.util.HashSet;

@Configuration
@ComponentScan(basePackageClasses = {TireServiceTest.class})
@Import(TestConfig.class)
public class TireDataTestConfig {

    @Inject
    TireManagerDao tmd;

    @Bean
    public TireDataCache createTireDataCache() {

        TireDataCache tdc = new TireDataCache();
        tdc.setAllTireManufacturers(new HashSet<>(tmd.retrieveAllTireManufacturers()));
        tdc.setAllTireProperties(new HashSet<>(tmd.retrieveAllTireProperties()));
        return tdc;

    }

}
