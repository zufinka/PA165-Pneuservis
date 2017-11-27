package config;

import cz.muni.fi.pa165.pneuservis.backend.dao.OrderDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.*;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class OrderDaoTestConfig {

    @Bean
    OrderDao orderDao() {

        String dateString1 = "08.02.1995 12:35";
        String dateString2 = "11.07.1995 07:41";
        String dateString3 = "13.01.1995 19:06";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime date1 = LocalDateTime.parse(dateString1, formatter);
        LocalDateTime date2 = LocalDateTime.parse(dateString2, formatter);
        LocalDateTime date3 = LocalDateTime.parse(dateString3, formatter);

        TireManufacturer tireManufacturer = new TireManufacturer("Continental");
        TireProperties tireProperties = new TireProperties(VehicleTypeEnum.P, 165, 95, 16, 95, SpeedClassEnum.H, SeasonEnum.WINTER);
        Tire tire = new Tire("WXR", tireManufacturer, tireProperties, 10, new BigDecimal(10.568));

        Service service = new Service("test service", TypeOfServiceEnum.TIRECHANGE);
        service.setPrice(BigDecimal.ONE);

        OrderItem orderItem1 = new OrderItem(tire, 1L);
        OrderItem orderItem2 = new OrderItem(service, 2L);
        OrderItem orderItem3 = new OrderItem(tire, 3L);
        OrderItem orderItem4 = new OrderItem(service, 4L);

        List<OrderItem> orderItemList1 = new ArrayList<>();
        List<OrderItem> orderItemList2 = new ArrayList<>();
        List<OrderItem> orderItemList3 = new ArrayList<>();
        
        orderItemList1.add(orderItem1);
        orderItemList1.add(orderItem2);
        
        orderItemList2.add(orderItem2);
        orderItemList2.add(orderItem3);
        orderItemList2.add(orderItem4);
        
        orderItemList3.add(orderItem1);
        orderItemList3.add(orderItem2);
        orderItemList3.add(orderItem3);
        orderItemList3.add(orderItem4);

        Customer customer1 = new Customer("meow", "meow", "meow", "meow", "meow", "meow", "meow", "meow");
        Customer customer2 = new Customer("woof", "woof", "woof", "woof", "woof", "woof", "woof", "woof");

        Order order1 = new Order(date1, customer1, orderItemList1);
        Order order2 = new Order(date2, customer2, orderItemList2);
        Order order3 = new Order(date3, customer2, orderItemList3);

        Long order1Id = 31L;
        Long order2Id = 9L;
        Long order3Id = 13L;
        Long nonExistentOrderId = 666L;

        OrderDao orderDao = mock(OrderDao.class);

        when(orderDao.getById(order1Id)).thenReturn(order1);
        when(orderDao.getById(order2Id)).thenReturn(order2);
        when(orderDao.getById(order3Id)).thenReturn(order3);
        when(orderDao.getAll()).thenReturn(Arrays.asList(order1, order2, order3));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            Order order = (Order) argument;
            if (order.getId() != null) {
                throw new NullPointerException("ID must be null when creating.");
            }
            order.setId(order1Id);
            return null;
        }).when(orderDao).create(any(Order.class));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            Order order = (Order) argument;
            if (order.getId() == null) {
                throw new NullPointerException("ID can't be null when updating.");
            }
            return null;
        }).when(orderDao).update(any(Order.class));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            Order order = (Order) argument;
            if (order.getId() == null) {
                throw new NullPointerException("ID can't be null when deleting.");
            }
            orderDao.getById(order.getId());
            return null;
        }).when(orderDao).delete(any(Order.class));

        return orderDao;
    }
}
