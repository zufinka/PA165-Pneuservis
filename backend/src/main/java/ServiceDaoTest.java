
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.enums.typeOfServiceEnum;
import static org.junit.Assert.*;
import org.junit.Test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zuzana Žufanová, zufinka@mail.muni.cz
 */
public class ServiceDaoTest {
    private ServiceDaoImpl serviceMan;

    private Service createInitializedService(){
        Service service = new Service();
        service.setName("baf");
        service.setPrice(BigDecimal.valueOf(2500));
        service.setServiceType(typeOfServiceEnum.TIRECHANGE);
        return service;
    }

    @Test
    @Expected (NullPointerException.class)
    public void createNullService(){
        serviceMan.createService(null);
    }

    @Test
    @Expected (NullPointerException.class)
    public void findByNullId(){
        serviceMan.findServiceById(null);
    }

    @Test
    @Expected (NullPointerException.class)
    public void findByNullName(){
        serviceMan.findByName(null);
    }

    @Test
    @Expected (NullPointerException.class)
    public void findByNullPrice(){
        serviceMan.findByPrice(null);
    }

    @Test
    @Expected (NullPointerException.class)
    public void findByNullTypeOfService(){
        serviceMan.findByTypeofService(null);
    }

    @Test
    public void findByCorrectId(){
        Service service = createInitializedService();
        serviceMan.createService(service);
        Service foundService = serviceMan.findServiceById(service.getId());
        assertEquals(service, foundService);
        assertNotSame(service, foundService);
    }

    @Test
    public void findByCorrectName(){
        Service service = createInitializedService();
        serviceMan.createService(service);
        Service foundService = serviceMan.findByName(service.getName());
        assertEquals(service, foundService);
        assertNotSame(service, foundService);
    }

    @Test
    public void findByCorrectPrice(){
        Service service = createInitializedService();
        serviceMan.createService(service);
        List<Service> foundServices = serviceMan.findByPrice(service.getPrice());
        assertEquals(service, foundServices.get(0));
        assertNotSame(service, foundServices.get(0));
    }

    @Test
    public void findByCorrectTypeOfService(){
        Service service = createInitializedService();
        serviceMan.createService(service);
        Service foundService = serviceMan.findByTypeOfService(service.getServiceType());
        assertEquals(service, foundService);
        assertNotSame(service, foundService);
    }

    @Test
    @Expected (NullPointerException.class)
    public void deleteNullService(){
        serviceMan.deleteService(null);
    }

    @Test
    @Expected (IllegalArgumentException.class)
    public void deleteNonExistingService(){
        Service service = createInitializedService();
        serviceMan.deleteService(service);
    }

    @Test
    public void deleteServiceTest(){
        Service service = createInitializedService();
        serviceMan.createService(service);
        serviceMan.deleteService(service);
        Service foundService = serviceMan.findServiceById(service.getId());
        assertEquals(foundService, null);
    }

    @Test
    @Expected (NullPointerException.class)
    public void updateNullService(){
        serviceMan.updateService(null);
    }

    @Test
    @Expected (IllegalArgumentException.class)
    public void updateNonExistingService(){
        Service service = createInitializedService();
        serviceMan.updateService(service);
    }

    @Test
    public void updateServiceTest(){
        Service service = createInitializedService();
        serviceMan.createService(service);
        service.setName("lek");
        serviceMan.updateService(service);
        assertEquals("lek", serviceMan.findServiceById(service).getName());
    }

    @Test
    public void retrieveAllServicesTest(){
        Service service1 = createInitializedService();
        Service service2 = createInitializedService();
        service2.setName("lek");
        serviceMan.createService(service1);
        serviceMan.createService(service2);
        List<Service> services = new ArrayList<>();
        services.add(service1);
        services.add(service2);
        assertEquals(services, serviceMan.retrieveAllServices());
    }
}
