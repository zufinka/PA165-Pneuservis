package services;


import config.TestConfig;
import config.TireDataTestConfig;
import config.TireServiceTestConfig;
import cz.muni.fi.pa165.pneuservis.backend.entity.Tire;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;
import exceptions.NoSuchObjectInDatabaseException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/***
 * @author Martin Zilak, 433372@mail.muni.cz
 */

public class TireServiceTest {

    private static TireService ts;

    @BeforeClass
    public static void init() {
        ApplicationContext apx = new AnnotationConfigApplicationContext(TireServiceTestConfig.class, TireDataTestConfig.class, TestConfig.class);
        ts = apx.getBean(TireService.class);
    }

    @Test
    public void testGetAllTires() {
        List<Tire> tires = ts.getAllTires();
        assertEquals(tires.size(), 5);
    }

    @Test
    public void testGetAllManufacturers() {
        Set<TireManufacturer> m = ts.getAllTireManufacturers();
        assertEquals(m.size(), 3);
    }

    @Test
    public void testGetAllProperties() {
        Set<TireProperties> p = ts.getAllTireProperties();
        assertEquals(p.size(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void purchaseTireNegativeIDTest() {
        ts.purchaseTire(new Long(-1), 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void purchaseTireNegativeNoPneusTest() {
        ts.purchaseTire(new Long(1), -10);
    }

    @Test(expected = NoSuchObjectInDatabaseException.class)
    public void purchaseTireNotInDBTest() {
        ts.purchaseTire(new Long(500), 10);
    }

    @Test
    public void purchaseTireNotEnoughTiresTest() {
        assertEquals(ts.purchaseTire(new Long(1), 100), false);
    }

    @Test
    public void purchaseTireTest() {
        assertEquals(ts.purchaseTire(new Long(1), 1), true);
    }

    @Test
    public void fidTireByPropertiesNullTest() {
        List<Tire> t = ts.findTireByProperties(null, null);
        assertEquals(t.size(), 5);
    }

    @Test
    public void fidTireByManufTest() {
        List<Tire> t = ts.findTireByProperties(new TireManufacturer("Manuf 1"), null);
        assertEquals(t.size(), 2);
    }

    @Test
    public void fidTireByBothTest() {
        List<TireProperties> props = new ArrayList<>(ts.getAllTireProperties());
        List<Tire> t = ts.findTireByProperties(new TireManufacturer("Manuf 1"), props);
        assertEquals(t.size(), 2);
    }

}
