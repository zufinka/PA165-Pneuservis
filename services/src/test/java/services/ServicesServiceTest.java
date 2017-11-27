package services;

import config.ServiceConfiguration;
import config.TireDataConfig;
import cz.muni.fi.pa165.pneuservis.backend.dao.ServiceDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doAnswer;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */

@ContextConfiguration(classes = {ServiceConfiguration.class, TireDataConfig.class})
public class ServicesServiceTest{

    @Mock
    private ServiceDao serviceDao;

    @Inject
    @InjectMocks
    private ServicesServiceImpl servicesService;

    @Mock
    private Service service1, service2, service3, serviceNotInDatabase;

    @BeforeMethod
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        createInitializedService();

        when(serviceDao.findServiceById(service1.getId())).thenReturn(service1);
        when(serviceDao.findServiceById(service2.getId())).thenReturn(service2);
        when(serviceDao.findServiceById(null)).thenThrow(NullPointerException.class);
        when(serviceDao.findServiceById(25L)).thenReturn(null);
        when(serviceDao.retrieveAllServices()).thenReturn(Arrays.asList(service1, service2));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            Service service = (Service) argument;
            service.setId(5L);
            return null;
        }).when(serviceDao).createService(any(Service.class));

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            Service service = (Service) argument;
            if (service.getId() == null) {
                throw new NullPointerException("ID can't be null when updating.");
            }
            return null;
        }).when(serviceDao).updateService(any(Service.class));

        doNothing().when(serviceDao).deleteService(any(Service.class));
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

        service3 = new Service();
        service3.setName("stouch");
        service3.setPrice(BigDecimal.valueOf(1500));
        service3.setServiceType(TypeOfServiceEnum.CLEANING);
        service3.setProcessingTime(Duration.ofHours(2));

        serviceNotInDatabase = new Service();
        serviceNotInDatabase.setName("fuj");
        serviceNotInDatabase.setPrice(BigDecimal.valueOf(800));
        serviceNotInDatabase.setServiceType(TypeOfServiceEnum.OILCHANGE);
        serviceNotInDatabase.setProcessingTime(Duration.ofHours(5));
        serviceNotInDatabase.setId(42L);
    }

    @Test
    public void getServiceTest(){
        service2.setId(2L);
        Service service = servicesService.getService(service2.getId());
        assertEquals(service, service2);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void getServiceByNullId(){
        servicesService.getService((Long)null);
    }

    @Test
    public void createServiceTest(){
        service1.setId(null);
        servicesService.create(service1);
        verify(serviceDao).createService(service1);
        verifyNoMoreInteractions(serviceDao);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void createNullService(){
        servicesService.create(null);
    }

    @Test
    public void updateServiceTest(){
        service2.setId(null);
        servicesService.create(service2);
        service2.setName("au");
        servicesService.update(service2);
        assertEquals(servicesService.getService(service2.getId()).getName(), "au");
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void updateNullService(){
        servicesService.update(null);
    }

    @Test
    public void deleteServiceTest(){
        servicesService.delete(service3);
        List<Service> services = servicesService.getAllServices();
        assertEquals(2, services.size());
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void deleteNullService(){
        servicesService.delete(null);
    }

    @Test
    public void getAllServicesTest(){
        assertEquals(servicesService.getAllServices(), Arrays.asList(service1, service2));
    }
}
