package facade;

import config.ServiceConfiguration;
import config.TireDataConfig;
import dto.TireManufacturerDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

/***
 * @author Martin Zilak, 433372@mail.muni.cz
 */

public class TireFacadeTest {

    static TireFacade tf;

    @BeforeClass
    public static void init() {
        ApplicationContext apx = new AnnotationConfigApplicationContext(ServiceConfiguration.class, TireDataConfig.class);
        tf = apx.getBean(TireFacade.class);
    }

    @Test
    public void getAllTiresTest() {
        assertEquals(tf.getAllTires().size(), 5);
    }

    @Test
    public void getAllTireManufTest() {
        assertEquals(tf.getAllTireManufacturers().size(), 3);
    }

    @Test
    public void getAllTirePropsTest() {
        assertEquals(tf.getAllTireProperties().size(), 2);
    }

    @Test
    public void purchaseTireTest() {
        assertEquals(tf.purchaseTire(new Long(1), 3), true);
    }

    @Test
    public void findByPropsTest() {
        TireManufacturerDTO tmf = new TireManufacturerDTO("Manuf 1");
        tmf.setId(new Long(1));
        assertEquals(tf.findTireByProperties(tmf, null).size(), 2);
    }

}