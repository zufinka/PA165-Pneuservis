package config;

import api.TireFacade;
import fascadeImpl.TireFacadeImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import services.MappingService;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

@Configuration
@ComponentScan(basePackageClasses = {TireFacadeImpl.class})
public class MainConfig {
}
