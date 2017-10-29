
package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        if (tire.getTireManufacturer() == null) {
            throw new IllegalArgumentException("manufacturer in tire is null");
        }
        if (tire.getTireProperties() == null) {
            throw new IllegalArgumentException("tire properties in tire is null");
        }

        if (findTireByAttrs(tire) != null) {
            return false;
        }

        em.getTransaction().begin();

        TireManufacturer tmf = findTireManuf(tire.getTireManufacturer());
        if (tmf == null) {
            em.persist(tire.getTireManufacturer());
        } else {
            tire.setTireManufacturer(tmf);
        }

        TireProperties tp = findTireProps(tire.getTireProperties());
        if (tp == null) {
            em.persist(tire.getTireProperties());
        } else {
            tire.setTireProperties(tp);
        }

        em.persist(tire);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public Tire findTireById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        try {
            return em.createQuery("SELECT t FROM Tire t where t.id = :id", Tire.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Tire findTireByAttrs(Tire tire) throws IllegalArgumentException {
        if (tire == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (tire.getTireManufacturer() == null) {
            throw new IllegalArgumentException("manufacturer in tire is null");
        }
        if (tire.getTireProperties() == null) {
            throw new IllegalArgumentException("tire properties in tire is null");
        }

        em.getTransaction().begin();

        TireManufacturer tm = findTireManuf(tire.getTireManufacturer());
        TireProperties tp = findTireProps(tire.getTireProperties());
        if (tm == null || tp == null) {
            em.getTransaction().commit();
            return null;
        }

        Tire t = null;
        try {
            t = em.createQuery("SELECT t FROM Tire t WHERE "
                    + "t.manufacturer = :tm "
                    + "AND t.tireProperties = :tp "
                    + "AND t.name = :n "
                    //+ "AND t.price = :p"
                    ,Tire.class)
                    .setParameter("n", tire.getName())
                    .setParameter("tp", tp)
                    .setParameter("tm", tm)
                    //.setParameter("p", tire.getPrice())
                    .getSingleResult();
        } catch (NoResultException nre) {
        }
        em.getTransaction().commit();
        return t;

    }

    @Override
    public boolean updateTire(Tire tire) throws IllegalArgumentException {
        if (tire == null) {
            throw new IllegalArgumentException("tire is null");
        }

        em.getTransaction().begin();
        em.persist(tire);
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
    public boolean deleteTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("tire is null");
        }

        em.getTransaction().begin();
        em.remove(tire);
        em.getTransaction().commit();

        return true;
    }

    private TireManufacturer findTireManuf(TireManufacturer tireManufacturer) {
        //em.getTransaction().begin();
        TireManufacturer tm = null;
        try {
            tm = em.createQuery("SELECT m FROM TireManufacturer m where m.name = :manName", TireManufacturer.class)
                    .setParameter("manName", tireManufacturer.getName()).getSingleResult();
        } catch (NoResultException nre) {
        }
        //em.getTransaction().commit();
        return tm;
    }

    private TireProperties findTireProps(TireProperties tireProperties) {
        //em.getTransaction().begin();
        TireProperties tp = null;
        try {
            tp = em.createQuery("SELECT t FROM TireProperties t where "
                    + "t.aspectRatio = :ar "
                    + "AND t.diameter = :d "
                    + "AND t.loadIndex = :li "
                    + "AND t.season = :s "
                    + "AND t.speedClass = :sc "
                    + "AND t.tireVehicleType = :twt "
                    + "AND t.width = :w", TireProperties.class)
                    .setParameter("ar", tireProperties.getAspectRatio())
                    .setParameter("d", tireProperties.getDiameter())
                    .setParameter("li", tireProperties.getLoadIndex())
                    .setParameter("s", tireProperties.getSeason())
                    .setParameter("sc", tireProperties.getSpeedClass())
                    .setParameter("twt", tireProperties.getTireVehicleType())
                    .setParameter("w", tireProperties.getWidth()).getSingleResult();
        } catch (NoResultException nre) {
        }
        //em.getTransaction().commit();
        return tp;
    }
}
