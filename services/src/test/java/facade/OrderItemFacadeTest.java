package facade;

import config.ServiceConfiguration;
import cz.muni.fi.pa165.pneuservis.backend.entity.*;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import dto.OrderItemDTO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.MappingService;
import services.OrderItemService;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderItemFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private OrderItemService orderItemService;

    @Spy
    @Inject
    private MappingService mappingService;

    private Tire tire;
    private Service service;
    private OrderItem orderItem1, orderItem2;

    @InjectMocks
    private OrderItemFacade orderItemFacade = new OrderItemFacadeImpl();

    @BeforeMethod
    public void setUp() {
        prepareOrderItem();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOrderItemTest() {
        OrderItemDTO orderItemDTO = mappingService.mapTo(orderItem1, OrderItemDTO.class);
        orderItemFacade.create(orderItemDTO);
        verify(orderItemService).create(orderItem1);
    }

    @Test
    public void updateOrderItemTest() {
        OrderItemDTO orderItemDTO = mappingService.mapTo(orderItem1, OrderItemDTO.class);
        orderItemFacade.update(orderItemDTO);
        verify(orderItemService).update(orderItem1);
    }

    @Test
    public void deleteOrderItemTest() {
        OrderItemDTO orderItemDTO = mappingService.mapTo(orderItem1, OrderItemDTO.class);
        orderItemFacade.delete(orderItemDTO);
        verify(orderItemService).delete(orderItem1);
    }

    @Test
    public void getOrderItemTest() {
        when(orderItemService.getOrderItem(any(Long.class))).thenReturn(orderItem1);
        orderItemFacade.getOrderItem(1L);
        verify(orderItemService).getOrderItem(1L);
    }

    private void prepareOrderItem() {
        TireManufacturer tireManufacturer = new TireManufacturer("Continental");
        TireProperties tireProperties = new TireProperties(VehicleTypeEnum.P, 165, 95, 16, 95, SpeedClassEnum.H, SeasonEnum.WINTER);
        tire = new Tire("WXR", tireManufacturer, tireProperties, 10, new BigDecimal(10.568));

        service = new Service("test service", TypeOfServiceEnum.TIRECHANGE);
        service.setPrice(BigDecimal.ONE);

        orderItem1 = new OrderItem(tire, 5L);
        orderItem2 = new OrderItem(service, 4L);
    }
}
