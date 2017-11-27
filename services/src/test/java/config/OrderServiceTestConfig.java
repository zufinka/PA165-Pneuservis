package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.OrderService;
import services.OrderServiceImpl;

@Configuration
@Import(OrderDaoTestConfig.class)
public class OrderServiceTestConfig {

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl();
    }
}
