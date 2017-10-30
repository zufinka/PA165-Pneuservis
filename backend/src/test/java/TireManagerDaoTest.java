import cz.muni.fi.pa165.pneuservis.backend.PersistenceApplicationContext;
import cz.muni.fi.pa165.pneuservis.backend.dao.TireManagerDao;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    @Test
    public void test() {
        Assert.assertNotNull(tireManagerDao);
    }
}
