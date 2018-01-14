
package cz.muni.fi.pa165.pneuservis.backend.dao;

import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
@Repository
@Transactional
public class TireManagerDaoImpl implements TireManagerDao {

    @PersistenceContext
    private EntityManager em;

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

    private Tire findTireByAttrs(Tire tire) throws IllegalArgumentException {
        if (tire == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (tire.getTireManufacturer() == null) {
            throw new IllegalArgumentException("manufacturer in tire is null");
        }
        if (tire.getTireProperties() == null) {
            throw new IllegalArgumentException("tire properties in tire is null");
        }

        TireManufacturer tm = findTireManuf(tire.getTireManufacturer());
        TireProperties tp = findTireProps(tire.getTireProperties());
        if (tm == null || tp == null) {
            return null;
        }

        Tire t = null;
        try {
            t = em.createQuery("SELECT t FROM Tire t WHERE "
                            + "t.manufacturer = :tm "
                            + "AND t.tireProperties = :tp "
                            + "AND t.name = :n "
                    //+ "AND t.price = :p"
                    , Tire.class)
                    .setParameter("n", tire.getName())
                    .setParameter("tp", tp)
                    .setParameter("tm", tm)
                    .getSingleResult();
        } catch (NoResultException nre) {
        }
        return t;

    }

    @Override
    public boolean updateTire(Tire tire) throws IllegalArgumentException {
        if (tire == null) {
            throw new IllegalArgumentException("tire is null");
        }

        em.persist(tire);

        return true;
    }

    @Override
    public List<Tire> retrieveAllTires() {
        List<Tire> tires = em.createQuery("SELECT t FROM Tire t", Tire.class).getResultList();
        return tires;
    }

    @Override
    public boolean deleteTire(Tire tire) {
        if (tire == null) {
            throw new IllegalArgumentException("tire is null");
        }

        em.remove(tire);

        return true;
    }

    @Override
    public List<Tire> findTiresByProperties(TireManufacturer tireManufacturer, TireProperties tireProperties) {
        if (tireManufacturer != null && tireProperties == null) return findTireByManufacturer(tireManufacturer);
        if (tireManufacturer == null && tireProperties != null) return (findTireByTireProperties(tireProperties));
        if (tireManufacturer != null && tireProperties != null) return (findTireByTirePropertiesandTireManufacturer(tireManufacturer, tireProperties));
        return new ArrayList<>();
    }

    @Override
    public List<TireManufacturer> retrieveAllTireManufacturers() {
        List<TireManufacturer> manufacturers = em.createQuery("SELECT m FROM TireManufacturer m", TireManufacturer.class).getResultList();
        return manufacturers;
    }

    @Override
    public List<TireProperties> retrieveAllTireProperties() {
        List<TireProperties> tireProperties = em.createQuery("SELECT p FROM TireProperties p", TireProperties.class).getResultList();
        return tireProperties;
    }

    private List<Tire> findTireByManufacturer(TireManufacturer tireManufacturer) {
        return em.createQuery("SELECT t FROM Tire t where t.manufacturer = :m", Tire.class)
                .setParameter("m", tireManufacturer).getResultList();
    }

    private List<Tire> findTireByTireProperties(TireProperties tireProperties) {
        return em.createQuery("SELECT t FROM Tire t where t.tireProperties = :tp", Tire.class)
                .setParameter("tp", tireProperties).getResultList();
    }

    private List<Tire> findTireByTirePropertiesandTireManufacturer(TireManufacturer tireManufacturer, TireProperties tireProperties) {
        return em.createQuery("SELECT t FROM Tire t where t.tireProperties = :tpid AND t.manufacturer = :tmid", Tire.class)
                .setParameter("tmid", tireManufacturer)
                .setParameter("tpid", tireProperties).getResultList();
    }

    private TireManufacturer findTireManuf(TireManufacturer tireManufacturer) {
        TireManufacturer tm = null;
        try {
            tm = em.createQuery("SELECT m FROM TireManufacturer m where m.name = :manName", TireManufacturer.class)
                    .setParameter("manName", tireManufacturer.getName()).getSingleResult();
        } catch (NoResultException nre) {
        }
        return tm;
    }

    private TireProperties findTireProps(TireProperties tireProperties) {
        TireProperties tp = null;
        try {
            tp = em.createQuery("SELECT t FROM TireProperties t where "
                    + "t.aspectRatio = :ar "
                    + "AND t.diameter = :d "
                    + "AND t.loadIndex = :li "
                    + "AND t.season = :s "
                    + "AND t.speedClass = :sc "
                    + "AND t.vehicleType = :twt "
                    + "AND t.width = :w", TireProperties.class)
                    .setParameter("ar", tireProperties.getAspectRatio())
                    .setParameter("d", tireProperties.getDiameter())
                    .setParameter("li", tireProperties.getLoadIndex())
                    .setParameter("s", tireProperties.getSeason())
                    .setParameter("sc", tireProperties.getSpeedClass())
                    .setParameter("twt", tireProperties.getVehicleType())
                    .setParameter("w", tireProperties.getWidth()).getSingleResult();
        } catch (NoResultException nre) {
        }
        return tp;
    }
}
