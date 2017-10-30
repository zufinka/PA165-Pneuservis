package cz.muni.fi.pa165.pneuservis.backend.dao;

import static com.google.common.base.Preconditions.checkNotNull;
import cz.muni.fi.pa165.pneuservis.backend.entity.Service;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/***
 * @author Róbert Ivan , 461468@mail.muni.cz
 */

public class ServiceDaoImpl implements ServiceDao{

    @PersistenceContext
    private EntityManager em;
    
    public ServiceDaoImpl(EntityManager em) {
        this.em = em;

    }
    
    @Override
    public void createService(Service service){
        checkNotNull(service);
        em.persist(service);   
    }

    @Override
    public Service findServiceById(Long id) {
        checkNotNull(id);
        return em.find(Service.class, id);
    }

    @Override
    public Service findByName(String name) {
        if (name == null){
            throw new IllegalArgumentException("Name is null.");
        }
        Service fservice =  em.createQuery("SELECT s FROM Service s " +
                "WHERE s.name = :name", Service.class).getSingleResult();
        return fservice;
    }
    
    @Override
    public List<Service> findByPrice(BigDecimal price) {
        if (price == null){
            throw new IllegalArgumentException("Price is null.");
        }
        List<Service> fservice =  em.createQuery("SELECT s FROM Service s " +
                "WHERE s.price = :price", Service.class).getResultList();
        return fservice;
    }
    
    @Override
    public Service findByTypeofService(TypeOfServiceEnum serviceType) {
        if (serviceType == null){
            throw new IllegalArgumentException("service type is null.");
        }
        Service fservice =  em.createQuery("SELECT s FROM Service s " +
                "WHERE s.serviceType = :serviceType", Service.class).getSingleResult();
        return fservice;
    }
    
    @Override
    public List<Service> retrieveAllServices() {
        em.getTransaction().begin();
        List<Service> services = em.createQuery("SELECT s FROM Service s", Service.class).getResultList();
        em.getTransaction().commit();
        return services;
    }

    @Override
    public void updateService(Service service) throws IllegalArgumentException {
        checkNotNull(service);
        em.merge(service); 
    }

    @Override
    public void deleteService(Service service) {        
        checkNotNull(service);
        em.remove(service); 
    }
    
}
