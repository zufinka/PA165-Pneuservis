/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pneuservis.backend.entity.tire;

import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.TireVehicleTypeEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import cz.muni.fi.pa165.pneuservis.backend.dao.TireManagerDaoImpl;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public class TryOut {
    
    private static EntityManagerFactory emf;
    
    public static void main(String[] args){
    
        new AnnotationConfigApplicationContext(InMemoryDB.class);
        emf = Persistence.createEntityManagerFactory("default");
        
        Tire t1 = new Tire("WXR", new TireManufacturer("Continental"), new TireProperties(
                TireVehicleTypeEnum.P, 165, 95, 16, 95,  SpeedClassEnum.H, SeasonEnum.WINTER), 10, new BigDecimal(10.568));
        Tire t2 = new Tire("TVOJA BABKA", new TireManufacturer("Continental"), new TireProperties(
                TireVehicleTypeEnum.P, 170, 95, 16, 95,  SpeedClassEnum.H, SeasonEnum.WINTER), 10, new BigDecimal(11.568));
        Tire t3 = new Tire("DZAMES", new TireManufacturer("Continental"), new TireProperties(
                TireVehicleTypeEnum.P, 165, 95, 16, 95,  SpeedClassEnum.H, SeasonEnum.WINTER), 8, new BigDecimal(50.568));
        
        EntityManager em = emf.createEntityManager();
        TireManagerDaoImpl tm = new TireManagerDaoImpl(em);
        tm.createTire(t1);
        tm.createTire(t2);
        
        System.out.println(tm.findTireById(new Long(1)));
        System.out.println(tm.findTireByAttrs(t1));
        
        t1.setPrice(BigDecimal.ONE);
        tm.updateTire(t1);
        System.out.println(tm.retrieveAllTires());
        
        tm.deleteTire(t2);
        System.out.println(tm.retrieveAllTires());
 
        
        em.close();
        emf.close();
        
    }
    
}
