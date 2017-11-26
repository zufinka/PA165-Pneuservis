
package facade;

import dto.ServiceDTO;
import java.util.List;

/***
 * @author Róbert Ivan , 461468@mail.muni.cz
 */
public interface ServiceFacade {
    /**
     * Retrieves a service based on ID.
     * @param id of service
     * @return 
     */
    ServiceDTO getService(long id);
    /**
     * Returns list of all Services in database
     * @return
     */
    List<ServiceDTO> getAllServices();
    
    /**
     * Creates a service.
     * @param service
     */
    
    void create(ServiceDTO service);

    /**
     * Modifies the specified service.
     * @param service
     */
    void update(ServiceDTO service);

    /**
     * Removes the specified service.
     * @param service
     */ 
    void delete(ServiceDTO service);
}
