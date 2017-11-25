package config;

import facade.TireFacadeImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

@Configuration
@ComponentScan(basePackageClasses = {TireFacadeImpl.class})
public class MainConfig {
}
