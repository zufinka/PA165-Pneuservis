/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pneuservis.backend.entity.Tire;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Kubo
 */
public class TryOut {
    
    private static EntityManagerFactory emf;
    
    public static void main(String[] args){
    
        new AnnotationConfigApplicationContext(InMemoryDB.class);
        emf = Persistence.createEntityManagerFactory("default");
        
        Tire t1 = new Tire(new Manufacturer("Continental"), new TireProperties(
                TireVehicleType.P, 165, 95, 16, 95,  SpeedClass.H, Season.WINTER), 10, new BigDecimal(10.568));
        Tire t2 = new Tire(new Manufacturer("Continental"), new TireProperties(
                TireVehicleType.P, 170, 95, 16, 95,  SpeedClass.H, Season.WINTER), 10, new BigDecimal(11.568));
        Tire t3 = new Tire(new Manufacturer("Continental"), new TireProperties(
                TireVehicleType.P, 165, 95, 16, 95,  SpeedClass.H, Season.WINTER), 8, new BigDecimal(50.568));
        
        EntityManager em = emf.createEntityManager();
        TireManagerDaoImpl tm = new TireManagerDaoImpl(em);
        tm.createTire(t1);
        tm.createTire(t2);
        
        tm.updateTire(t1, t3);
        
        System.out.println(tm.retrieveAllTires());
        
        tm.deleteTire(t2);
        
        em.close();
        emf.close();
        
    }
    
}
