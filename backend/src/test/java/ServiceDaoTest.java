import cz.muni.fi.pa165.pneuservis.backend.PersistenceApplicationContext;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.dao.*;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ServiceDaoTest {

    @Inject
    private ServiceDaoImpl serviceMan;

    @PersistenceContext
    private EntityManager em;

    private Service service;
    private Service service2;

    @BeforeMethod
    private Service createInitializedService(){
        service = new Service();
        service.setName("baf");
        service.setPrice(BigDecimal.valueOf(2500));
        service.setServiceType(TypeOfServiceEnum.TIRECHANGE);

        service = new Service();
        service.setName("lek");
        service.setPrice(BigDecimal.valueOf(500));
        service.setServiceType(TypeOfServiceEnum.OILCHANGE);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void createNullService(){
        serviceMan.createService(null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void findByNullId(){
        serviceMan.findServiceById(null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void findByNullName(){
        serviceMan.findByName(null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void findByNullPrice(){
        serviceMan.findByPrice(null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void findByNullTypeOfService(){
        serviceMan.findByTypeofService(null);
    }

    @Test
    public void findByCorrectId(){
        assertTrue(serviceMan.createService(service));
        Service foundService = serviceMan.findServiceById(service.getId());
        assertEquals(service, foundService);
    }

    @Test
    public void findByCorrectName(){
        serviceMan.createService(service);
        Service foundService = serviceMan.findByName(service.getName());
        assertEquals(service, foundService);
    }

    @Test
    public void findByCorrectPrice(){
        serviceMan.createService(service);
        List<Service> foundServices = serviceMan.findByPrice(service.getPrice());
        assertEquals(service, foundServices.get(0));
    }

    @Test
    public void findByCorrectTypeOfService(){
        serviceMan.createService(service);
        Service foundService = serviceMan.findByTypeOfService(service.getServiceType());
        assertEquals(service, foundService);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void deleteNullService(){
        serviceMan.deleteService(null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void deleteNonExistingService(){
        serviceMan.deleteService(service);
    }

    @Test
    public void deleteServiceTest(){
        serviceMan.createService(service);
        assertTrue(serviceMan.deleteService(service));
        Service foundService = serviceMan.findServiceById(service.getId());
        assertEquals(foundService, null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void updateNullService(){
        serviceMan.updateService(null);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void updateNonExistingService(){
        serviceMan.updateService(service);
    }

    @Test
    public void updateServiceTest(){
        serviceMan.createService(service);
        assertEquals(serviceMan.retrieveAllServices.get(0).getName(), "baf");
        service.setName("bafbaf");
        assertTrue(serviceMan.updateService(service));
        assertEquals("bafbaf", serviceMan.retrieveAllServices.get(0).getName());
    }

    @Test
    public void retrieveAllServicesTest(){
        serviceMan.createService(service1);
        serviceMan.createService(service2);
        List<Service> services = new ArrayList<>();
        services.add(service1);
        services.add(service2);
        assertEquals(services, serviceMan.retrieveAllServices());
    }
}