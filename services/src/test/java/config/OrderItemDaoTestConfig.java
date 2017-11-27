package config;

import cz.muni.fi.pa165.pneuservis.backend.dao.OrderItemDao;
import cz.muni.fi.pa165.pneuservis.backend.dao.OrderItemDaoImpl;
import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;
import org.mockito.InjectMocks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class OrderItemDaoTestConfig {

    @InjectMocks
    private OrderItem orderItem1;
    private OrderItem orderItem2;

    @Bean
    OrderItemDao orderItemDao(){
        OrderItemDao orderItemDao = mock(OrderItemDao.class);

        Long orderItem1Id = 31L;
        Long orderItem2Id = 9L;

        when(orderItemDao.getById(orderItem1Id)).thenReturn(orderItem1);
        when(orderItemDao.getById(orderItem2Id)).thenReturn(orderItem2);
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
