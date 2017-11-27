package facade;

import config.ServiceConfiguration;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import dto.ServiceDTO;
import org.junit.Before;
import org.mockito.Spy;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServiceFacadeTest {
    @Mock
    private ServicesService servicesService;

    @Inject
    @InjectMocks
    private ServiceFacade serviceFacade;

    @Mock
    @Inject
    private MappingService mappingService;

    private Service service1, service2;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        createInitializedService();

        when(servicesService.getService(service1.getId())).thenReturn(service1);
        when(servicesService.getService(service2.getId())).thenReturn(service2);
    }

    private void createInitializedService(){
        service1 = new Service();
        service1.setName("baf");
        service1.setPrice(BigDecimal.valueOf(3000));
        service1.setServiceType(TypeOfServiceEnum.TIRECHANGE);
        service1.setProcessingTime(Duration.ofHours(8));

        service2 = new Service();
        service2.setName("lek");
        service2.setPrice(BigDecimal.valueOf(500));
        service2.setServiceType(TypeOfServiceEnum.OILCHANGE);
        service2.setProcessingTime(Duration.ofHours(5));
    }

    @Test
    public void getServiceTest(){
        ServiceDTO serviceDTO = serviceFacade.getService(service2.getId());
        Service service = mappingService.mapTo(serviceDTO, Service.class);
        assertEquals(service, service2);
    }

    @Test
    public void getAllServicesTest(){
        List<ServiceDTO> servicesDTO = serviceFacade.getAllServices();
        List<Service> services = mappingService.mapTo(servicesDTO, Service.class);
        assertEquals(services, Arrays.asList(service1, service2));
    }

    @Test
    public void createServiceTest(){
        ServiceDTO service = mappingService.mapTo(service1, ServiceDTO.class);
        serviceFacade.create(service);
        verify(servicesService).create(service1);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void updateNullServiceTest(){
        serviceFacade.update(null);
    }

    @Test
    public void deleteServiceTest(){
        ServiceDTO service = mappingService.mapTo(service2, ServiceDTO.class);
        serviceFacade.create(service);
        serviceFacade.delete(service);
        verify(servicesService).delete(service2);
    }

}
