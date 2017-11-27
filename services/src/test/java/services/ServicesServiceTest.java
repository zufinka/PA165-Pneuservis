package services;

import config.ServiceConfiguration;
import cz.muni.fi.pa165.pneuservis.backend.dao.ServiceDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import exceptions.NoSuchObjectInDatabaseException;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServicesServiceTest extends AbstractTestNGSpringContextTests{
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
        Service service = servicesService.getService(service2.getId());
        assertEquals(service, service2);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void getServiceByNullId(){
        servicesService.getService(null);
    }

    @Test
    public void getServiceByNoExistentID(){
        Service service = servicesService.getService(25L);
        assertNull(service);
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
