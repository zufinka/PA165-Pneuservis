package config;

import cz.muni.fi.pa165.pneuservis.backend.dao.TireManagerDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.TireDataCache;
import services.TireService;
import services.TireServiceImpl;

import javax.inject.Inject;
import java.util.HashSet;

@Configuration
@Import(TireDaoTestConfig.class)
public class TireServiceTestConfig {

    @Bean
    public TireService tireService(){
        return new TireServiceImpl();
    }

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
