package config;

import cz.muni.fi.pa165.pneuservis.backend.PersistenceApplicationContext;
import cz.muni.fi.pa165.pneuservis.backend.entity.*;
import dto.*;
import facade.FacadePackageMarker;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.ServicePackageMarker;
import java.util.Collections;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */

@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {ServicePackageMarker.class, FacadePackageMarker.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
        mapper.addMapping(new DozerCustomConfig());
        return mapper;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Customer.class, CustomerDTO.class);
            mapping(Order.class, OrderDTO.class);
            mapping(OrderItem.class, OrderItemDTO.class);
            mapping(Service.class, ServiceDTO.class);
            mapping(Tire.class, TireDTO.class);
            mapping(TireManufacturer.class, TireManufacturerDTO.class);
            mapping(TireProperties.class, TirePropertiesDTO.class);
        }
    }
}
