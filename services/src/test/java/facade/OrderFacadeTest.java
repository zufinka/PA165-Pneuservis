package facade;

import config.ServiceConfiguration;
import config.TireDataConfig;
import cz.muni.fi.pa165.pneuservis.backend.entity.*;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import dto.OrderDTO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.MappingService;
import services.OrderService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private OrderService orderService;

    @Spy
    @Inject
    private MappingService mappingService;

    private LocalDateTime date1, date2;
    private Tire tire;
    private Service service;
    private List<OrderItem> orderList;
    private Customer customer1, customer2;
    private Order order1, order2;

    @InjectMocks
    private OrderFacade orderFacade = new OrderFacadeImpl();

    @BeforeMethod
    public void setUp() {
        prepareOrder();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOrderTest() {
        OrderDTO orderDTO = mappingService.mapTo(order1, OrderDTO.class);
        orderFacade.create(orderDTO);
        verify(orderService).create(order1);
    }

    @Test
    public void updateOrderTest() {
        OrderDTO orderDTO = mappingService.mapTo(order1, OrderDTO.class);
        orderFacade.update(orderDTO);
        verify(orderService).update(order1);
    }

    @Test
    public void deleteOrderTest() {
        OrderDTO orderDTO = mappingService.mapTo(order1, OrderDTO.class);
        orderFacade.delete(orderDTO);
        verify(orderService).delete(order1);
    }

    @Test
    public void getOrderTest() {
        when(orderService.getOrder(any(Long.class))).thenReturn(order1);
        OrderDTO orderDTO = mappingService.mapTo(order1, OrderDTO.class);
        orderFacade.getOrder(1L);
        verify(orderService).getOrder(1L);
    }

    private void prepareOrder() {
        String dateString1 = "08.02.1995 12:35";
        String dateString2 = "11.07.1995 07:41";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        date1 = LocalDateTime.parse(dateString1, formatter);
        date2 = LocalDateTime.parse(dateString2, formatter);

        TireManufacturer tireManufacturer = new TireManufacturer("Continental");
        TireProperties tireProperties = new TireProperties(VehicleTypeEnum.P, 165, 95, 16, 95, SpeedClassEnum.H, SeasonEnum.WINTER);
        tire = new Tire("WXR", tireManufacturer, tireProperties, 10, new BigDecimal(10.568));

        service = new Service("test service", TypeOfServiceEnum.TIRECHANGE);
        service.setPrice(BigDecimal.ONE);

        OrderItem orderItem1 = new OrderItem(tire, 5L);
        OrderItem orderItem2 = new OrderItem(service, 4L);

        orderList = new ArrayList<>();
        orderList.add(orderItem1);
        orderList.add(orderItem2);

        customer1 = new Customer("test", "test", "test", "test", "test", "test", "test", "test");
        customer2 = new Customer("blah", "blah", "blah", "blah", "blah", "blah", "blah", "blah");

        order1 = new Order(date1, customer1, orderList);
        order2 = new Order(date2, customer2, orderList);
    }
}
