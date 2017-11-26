package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.TireService;
import services.TireServiceImpl;
import services.TireServiceTest;

@Configuration
public class TireServiceTestConfig {

    @Bean
    public TireService tireService(){
        return new TireServiceImpl();
    }

}
