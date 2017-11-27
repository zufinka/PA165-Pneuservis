package services;

import config.ServiceConfiguration;
import cz.muni.fi.pa165.pneuservis.backend.dao.ServiceDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Order;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import exceptions.NoSuchObjectInDatabaseException;
import org.junit.Before;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doAnswer;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServicesServiceTest{

    @Mock
    private ServiceDao serviceDao;

    @Inject
    @InjectMocks
    private ServicesService servicesService;
    private Service service1, service2, serviceNotInDatabase;

    @Before
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
            if (service.getId() != null) {
                throw new NullPointerException("ID must be null when creating.");
            }
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

        doAnswer(invocation -> {
            Object argument = invocation.getArguments()[0];
            if (argument == null) {
                throw new NullPointerException("Argument can't be null.");
            }
            Service service = (Service) argument;
            if (service.getId() == null) {
                throw new NullPointerException("ID can't be null when deleting.");
            }
            serviceDao.findServiceById(service.getId());
            return null;
        }).when(serviceDao).deleteService(any(Service.class));
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

        Service serviceNotInDatabase = new Service();
        serviceNotInDatabase.setName("fuj");
        serviceNotInDatabase.setPrice(BigDecimal.valueOf(800));
        serviceNotInDatabase.setServiceType(TypeOfServiceEnum.OILCHANGE);
        serviceNotInDatabase.setProcessingTime(Duration.ofHours(5));
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

    @Test (expectedExceptions = NullPointerException.class)
    public void getServiceByNoExistentID(){
        Service service = servicesService.getService(25L);
    }

    @Test
    public void createServiceTest(){
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
        //service2.setId(null);
        servicesService.create(service2);
        service2.setName("au");
        servicesService.update(service2);
        assertEquals(servicesService.getService(service2.getId()).getName(), "au");
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void updateNullService(){
        servicesService.update(null);
    }

    @Test (expectedExceptions = NoSuchObjectInDatabaseException.class)
    public void updateNoExistentService(){
        servicesService.update(serviceNotInDatabase);
    }

    @Test
    public void deleteServiceTest(){
        //service2.setId(null);
        servicesService.create(service2);
        servicesService.delete(service2);
        verify(serviceDao).deleteService(service2);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void deleteNullService(){
        servicesService.delete(null);
    }

    @Test (expectedExceptions = NoSuchObjectInDatabaseException.class)
    public void deleteNoExistentService(){
        servicesService.delete(serviceNotInDatabase);
    }

    @Test
    public void getAllServicesTest(){
        assertEquals(servicesService.getAllServices(), Arrays.asList(service1, service2));
    }
}
