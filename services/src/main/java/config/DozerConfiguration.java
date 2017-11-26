package config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import services.MappingService;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

@Configuration
@ComponentScan(basePackageClasses = {MappingService.class})
public class DozerConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }
}
