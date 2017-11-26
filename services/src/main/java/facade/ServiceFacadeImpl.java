package facade;

import static com.google.common.base.Preconditions.checkNotNull;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import dto.ServiceDTO;
import java.util.List;
import javax.inject.Inject;
import services.MappingService;
import services.ServicesService;

/***
 * @author Róbert Ivan , 461468@mail.muni.cz
 */
public class ServiceFacadeImpl implements ServiceFacade{

    @Inject
    private ServicesService servicesService;
    
    @Inject
    private MappingService mappingService;
    
    @Override
    public ServiceDTO getService(long id){
        checkNotNull(id);
        Service services = servicesService.getService(id);
        return mappingService.mapTo(services, ServiceDTO.class);
    }
    @Override
    public List<ServiceDTO> getAllServices(){
        List<Service> services = servicesService.getAllServices();
        return mappingService.mapTo(services, ServiceDTO.class);
    }

    @Override
    public void create(ServiceDTO service){
        checkNotNull(service);
        Service s = mappingService.mapTo(service, Service.class);
        servicesService.create(s);
            
    }

    @Override
    public void update(ServiceDTO service){
        checkNotNull(service);
        Service s = mappingService.mapTo(service, Service.class);
        servicesService.update(s);
    }

    @Override
    public void delete(ServiceDTO service){
        checkNotNull(service);
        Service s = mappingService.mapTo(service, Service.class);
        servicesService.delete(s);
    }
}
