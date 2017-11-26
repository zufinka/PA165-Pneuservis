package services;

import static com.google.common.base.Preconditions.checkNotNull;
import cz.muni.fi.pa165.pneuservis.backend.dao.ServiceDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import java.util.List;
import javax.inject.Inject;

/***
 * @author Róbert Ivan , 461468@mail.muni.cz
 */
public class ServicesServiceImpl implements ServicesService{
    
    @Inject
    private ServiceDao ServiceDao;

    @Override
    public Service getService(long id) {
        checkNotNull(id);
        return ServiceDao.findServiceById(id);
    }

    @Override
    public void create(Service service) {
        checkNotNull(service);
        ServiceDao.createService(service);
    }

    @Override
    public void update(Service service) {
        checkNotNull(service);
        ServiceDao.updateService(service);
    }

    @Override
    public void delete(Service service) {
        checkNotNull(service);
        ServiceDao.updateService(service);
    }

    @Override
    public List<Service> getAllServices() {
        return ServiceDao.retrieveAllServices();
    }
    
}
