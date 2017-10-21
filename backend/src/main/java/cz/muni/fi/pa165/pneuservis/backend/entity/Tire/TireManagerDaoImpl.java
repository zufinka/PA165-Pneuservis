/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pneuservis.backend.entity.Tire;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public class TireManagerDaoImpl implements TireManagerDao {

    @PersistenceContext
    private EntityManager em;

    public TireManagerDaoImpl(EntityManager em) {
        this.em = em;
        
    }

    @Override
    public boolean createTire(Tire tire) throws IllegalArgumentException {
        if (tire == null) {
            throw new IllegalArgumentException("tire is null");
        }
        if (tire.getManufacturer() == null) {
            throw new IllegalArgumentException("manufacturer in tire is null");
        }
        if (tire.getTireProperties() == null) {
            throw new IllegalArgumentException("tire properties in tire is null");
        }

        em.getTransaction().begin();
        
        Manufacturer m;
        try{
            m = em.createQuery("SELECT m FROM Manufacturer m where m.name LIKE :manName", Manufacturer.class)
                .setParameter("manName", tire.getManufacturer().getName()).getSingleResult();
            tire.setManufacturer(m);
        }catch(NoResultException nre){
            em.persist(tire.getManufacturer());
        }
        
        TireProperties tp;
        try{
            tp = em.createQuery("SELECT t FROM TireProperties t WHERE t.internal_id = :intID", TireProperties.class)
                .setParameter("intID", tire.getTireProperties().getInternal_id()).getSingleResult();
            tire.setTireProperties(tp);
        }catch(NoResultException nre){
            em.persist(tire.getTireProperties());
            
        }
        
        em.persist(tire);
        em.getTransaction().commit();
        
        return true;
    }

    @Override
    public boolean updateTire(Tire old, Tire data) throws IllegalArgumentException {
        if (old == null) {
            throw new IllegalArgumentException("old is null");
        }
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        
        em.getTransaction().begin();
        Tire t;
        try{
            t = em.createQuery("SELECT t FROM Tire t WHERE t.internal_id = :intID", Tire.class)
                .setParameter("intID", old.getInternal_id()).getSingleResult();
        }catch(NoResultException nre){
            return false;
            
        }
        
        em.merge(t);
        t.setOnStock(data.getOnStock());
        t.setPrice(data.getPrice());
        t.setDescription(data.getDescription());
        t.setImage_url(data.getImage_url());
        em.persist(t);
        em.getTransaction().commit();
        
        return true;   
    }

    @Override
    public List<Tire> retrieveAllTires() {
        em.getTransaction().begin();
        List<Tire> tires = em.createQuery("SELECT t FROM Tire t", Tire.class).getResultList();
        em.getTransaction().commit();
        return tires;
    }

    @Override
    public List<Tire> getTiresByFilter(Tire filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("tire is null");
        }
        
        em.getTransaction().begin();
        Tire t;
        try{
            t = em.createQuery("SELECT t FROM Tire t WHERE t.internal_id = :intID", Tire.class)
                .setParameter("intID", tire.getInternal_id()).getSingleResult();
        }catch(NoResultException nre){
            return false;
            
        }
        em.remove(t);
        em.getTransaction().commit();
        
        return true; 
    }

    @Override
    public boolean deleteCategory(Manufacturer manufacturer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteCategory(TireProperties propetries) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
