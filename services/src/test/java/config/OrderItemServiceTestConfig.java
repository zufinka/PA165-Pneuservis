package config;

import cz.muni.fi.pa165.pneuservis.backend.dao.OrderItemDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.*;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.OrderItemService;
import services.OrderItemServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class OrderItemServiceTestConfig {

    @Bean
    public OrderItemService orderItemService(){
        return new OrderItemServiceImpl();
    }


    @Bean
    OrderItemDao orderItemDao(){

        TireManufacturer tireManufacturer = new TireManufacturer("Continental");
        TireProperties tireProperties = new TireProperties(VehicleTypeEnum.P, 165, 95, 16, 95, SpeedClassEnum.H, SeasonEnum.WINTER);
        Tire tire = new Tire("WXR", tireManufacturer, tireProperties, 10, new BigDecimal(10.568));

        Service service = new Service("test service", TypeOfServiceEnum.TIRECHANGE);
        service.setPrice(BigDecimal.ONE);

        OrderItem orderItem1 = new OrderItem(tire, 5L);
        OrderItem orderItem2 = new OrderItem(service, 4L);

        Long orderItem1Id = 31L;
        Long orderItem2Id = 9L;
        Long nonExistentOrderItemId = 666L;

        OrderItemDao orderItemDao = mock(OrderItemDao.class);

        when(orderItemDao.getById(orderItem1Id)).thenReturn(orderItem1);
        when(orderItemDao.getById(orderItem2Id)).thenReturn(orderItem2);
        when(orderItemDao.getById(nonExistentOrderItemId)).thenReturn(null);

        when(orderItemDao.getAll()).thenReturn(Arrays.asList(orderItem1, orderItem2));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            OrderItem orderItem = (OrderItem) argument;
            if (orderItem.getId() != null) {
                throw new NullPointerException("ID must be null when creating.");
            }
            orderItem.setId(orderItem1Id);
            return null;
        }).when(orderItemDao).create(any(OrderItem.class));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            OrderItem orderItem = (OrderItem) argument;
            if (orderItem.getId() == null) {
                throw new NullPointerException("ID can't be null when updating.");
            }
            return null;
        }).when(orderItemDao).update(any(OrderItem.class));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            OrderItem orderItem = (OrderItem) argument;
            if (orderItem.getId() == null) {
                throw new NullPointerException("ID can't be null when deleting.");
            }
            orderItemDao.getById(orderItem.getId());
            return null;
        }).when(orderItemDao).delete(any(OrderItem.class));

        return orderItemDao;
    }


}
