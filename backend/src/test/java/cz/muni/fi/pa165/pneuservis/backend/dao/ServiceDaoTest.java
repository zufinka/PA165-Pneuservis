package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.PersistenceApplicationContext;
import cz.muni.fi.pa165.pneuservis.backend.dao.ServiceDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ServiceDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private ServiceDao serviceMan;

    @PersistenceContext
    private EntityManager em;

    private Service service;
    private Service service2;

    @BeforeMethod
    private void createInitializedService(){
        service = new Service();
        service.setName("baf");
        service.setPrice(BigDecimal.valueOf(2500));
        service.setServiceType(TypeOfServiceEnum.TIRECHANGE);

        service2 = new Service();
        service2.setName("lek");
        service2.setPrice(BigDecimal.valueOf(500));
        service2.setServiceType(TypeOfServiceEnum.OILCHANGE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNullService(){
        serviceMan.createService(null);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void findByNullId(){
        serviceMan.findServiceById(null);
    }

    @Test
    public void findByCorrectId(){
        serviceMan.createService(service);
        Service foundService = serviceMan.findServiceById(service.getId());
        assertEquals(service, foundService);
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void deleteNullService(){
        serviceMan.deleteService(null);
    }

    @Test
    public void deleteServiceTest(){
        serviceMan.createService(service);
        serviceMan.deleteService(service);
        Service foundService = serviceMan.findServiceById(service.getId());
        assertEquals(foundService, null);
    }

    @Test
    public void updateServiceTest(){
        serviceMan.createService(service);
        assertEquals(serviceMan.retrieveAllServices().get(0).getName(), "baf");
        service.setName("bafbaf");
        serviceMan.updateService(service);
        assertEquals("bafbaf", serviceMan.retrieveAllServices().get(0).getName());
    }
}