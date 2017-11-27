package facade;

import config.ServiceConfiguration;
import config.TireDataConfig;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import dto.ServiceDTO;
import org.mockito.Spy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import services.MappingService;
import services.ServicesService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */

@ContextConfiguration(classes = {ServiceConfiguration.class, TireDataConfig.class})
public class ServiceFacadeTest extends AbstractTestNGSpringContextTests {
    @Mock
    private ServicesService servicesService;

    @Spy
    @Inject
    private MappingService mappingService;

    private Service service1, service2;

    @InjectMocks
    private ServiceFacade serviceFacade = new ServiceFacadeImpl();

    @BeforeMethod
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        createInitializedService();

        when(servicesService.getService(1L)).thenReturn(service1);
        when(servicesService.getService(2L)).thenReturn(service2);
        doNothing().when(servicesService).delete(any(Service.class));
    }

    private void createInitializedService(){
        service1 = new Service();
        service1.setName("baf");
        service1.setPrice(BigDecimal.valueOf(3000));
        service1.setServiceType(TypeOfServiceEnum.TIRECHANGE);
        service1.setProcessingTime(Duration.ofHours(8));
        service1.setId(1L);

        service2 = new Service();
        service2.setName("lek");
        service2.setPrice(BigDecimal.valueOf(500));
        service2.setServiceType(TypeOfServiceEnum.OILCHANGE);
        service2.setProcessingTime(Duration.ofHours(5));
        service2.setId(2L);
    }

    @Test
    public void getServiceTest(){
        ServiceDTO serviceDTO = mappingService.mapTo(service2, ServiceDTO.class);
        ServiceDTO service = serviceFacade.getService(2L);
        assertEquals(service, serviceDTO);
    }

    @Test
    public void getAllServicesTest(){
        List<ServiceDTO> services = serviceFacade.getAllServices();
        verify(servicesService).getAllServices();
    }

    @Test
    public void createServiceTest(){
        ServiceDTO serviceDTO = mappingService.mapTo(service1, ServiceDTO.class);
        serviceFacade.create(serviceDTO);
        verify(servicesService).create(service1);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void updateNullServiceTest(){
        serviceFacade.update(null);
    }

    @Test
    public void deleteServiceTest(){
        ServiceDTO service = mappingService.mapTo(service2, ServiceDTO.class);
        //serviceFacade.create(service);
        serviceFacade.delete(service);
        verify(servicesService).delete(service2);
    }

}
