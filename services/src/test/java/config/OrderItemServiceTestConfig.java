package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.OrderItemService;
import services.OrderItemServiceImpl;

@Configuration
@Import(OrderItemDaoTestConfig.class)
public class OrderItemServiceTestConfig {

    @Bean
    public OrderItemService orderItemService(){
        return new OrderItemServiceImpl();
    }

}
