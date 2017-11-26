package services;

import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import java.util.List;

/***
 * @author Róbert Ivan , 461468@mail.muni.cz
 */
public interface ServicesService {
    /**
     * Retrieves an services based on their ID.
     * @param id of service
     * @return 
     */
    Service getService(long id);

    /**
     * Creates an service.
     * @param service
     */
    void create(Service service);

    /**
     * Modifies the specified service.
     * @param service
     */
    void update(Service service);

    /**
     * Removes the specified service.
     * @param service
     */
    void delete(Service service);


    /**
     * Retrieves all services.
     * @return List of all services
     */
    List<Service> getAllServices(); 
}
