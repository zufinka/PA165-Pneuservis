import facade.TireFacade;
import config.DozerConfiguration;
import config.MainConfig;
import config.TireDataConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class main {

    public static void main(String[] args){
        ApplicationContext apx = new AnnotationConfigApplicationContext(MainConfig.class, TireDataConfig.class, DozerConfiguration.class);
        TireFacade tfi = apx.getBean(TireFacade.class);


        System.out.println(tfi.getAllTireManufacturers());
        System.out.println(tfi.getAllTireProperties());
        System.out.println(tfi.getAllTires());
        System.out.println(tfi.purchaseTire(new Long(1), 4));
        //System.out.println(tfi.findByProperties(new TireManufacturerDTO("Kokotnekolesa"), null));



    }

}
