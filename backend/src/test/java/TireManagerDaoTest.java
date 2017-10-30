import cz.muni.fi.pa165.pneuservis.backend.PersistenceApplicationContext;
import cz.muni.fi.pa165.pneuservis.backend.dao.TireManagerDao;
import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.VehicleTypeEnum;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.math.BigDecimal;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */

@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TireManagerDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private TireManagerDao tireManagerDao;

    @PersistenceContext
    private EntityManager entityManager;

    private TireManufacturer tireManufacturer;

    private Tire tire1;
    private Tire tire2;
    private Tire tire3;

    @BeforeMethod
    public void prepareTires() {
        tireManufacturer = new TireManufacturer("Continental");
        entityManager.persist(tireManufacturer);

        tire1 = new Tire("T1", tireManufacturer, new TireProperties(VehicleTypeEnum.P, 165, 95, 16, 95,  SpeedClassEnum.H, SeasonEnum.WINTER), 10, new BigDecimal(10.568));
        tire2 = new Tire("T2", tireManufacturer, new TireProperties(VehicleTypeEnum.P, 170, 95, 16, 95,  SpeedClassEnum.H, SeasonEnum.WINTER), 10, new BigDecimal(11.568));
        tire3 = new Tire("T3", tireManufacturer, new TireProperties(VehicleTypeEnum.P, 165, 95, 16, 95,  SpeedClassEnum.H, SeasonEnum.WINTER), 8, new BigDecimal(50.568));
    }

    @Test
    public void testCreateTire() {
        Assert.assertEquals(tireManagerDao.retrieveAllTires().size(), 0);
        Assert.assertTrue(tireManagerDao.createTire(tire1));
        Assert.assertEquals(tireManagerDao.retrieveAllTires().size(), 1);
        Assert.assertTrue(tireManagerDao.retrieveAllTires().get(0).equals(tire1));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNullTire() {
        tireManagerDao.createTire(null);
    }

    @Test
    public void testUpdateTire() {
        tireManagerDao.createTire(tire1);
        Assert.assertEquals(tireManagerDao.retrieveAllTires().get(0).getName(), "T1");
        tire1.setName("Pato Synek");
        Assert.assertTrue(tireManagerDao.updateTire(tire1));
        Assert.assertEquals(tireManagerDao.retrieveAllTires().get(0).getName(), "Pato Synek");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNullTire() {
        tireManagerDao.updateTire(null);
    }

    @Test
    public void testDeleteTire() {
        tireManagerDao.createTire(tire1);
        tireManagerDao.createTire(tire2);
        Assert.assertEquals(tireManagerDao.retrieveAllTires().size(), 2);
        Assert.assertTrue(tireManagerDao.deleteTire(tire1));
        Assert.assertEquals(tireManagerDao.retrieveAllTires().size(), 1);
        Assert.assertTrue(tireManagerDao.deleteTire(tire2));
        Assert.assertEquals(tireManagerDao.retrieveAllTires().size(), 0);
    }

    @Test
    public void testFindTireById() {
        tireManagerDao.createTire(tire1);
        Assert.assertEquals(tireManagerDao.findTireById(tire1.getId()), tire1);
    }

    @Test
    public void testRetrieveAllOrders() {
        tireManagerDao.createTire(tire1);
        tireManagerDao.createTire(tire2);
        tireManagerDao.createTire(tire3);
        Assert.assertEquals(tireManagerDao.retrieveAllTires().size(), 3);
        Assert.assertTrue(tireManagerDao.retrieveAllTires().get(0).equals(tire1));
        Assert.assertTrue(tireManagerDao.retrieveAllTires().get(1).equals(tire2));
        Assert.assertTrue(tireManagerDao.retrieveAllTires().get(2).equals(tire3));
    }
}
