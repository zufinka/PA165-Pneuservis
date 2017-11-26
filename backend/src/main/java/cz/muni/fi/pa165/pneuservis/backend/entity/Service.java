package cz.muni.fi.pa165.pneuservis.backend.entity;

import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/***
 * @author Róbert Ivan , 461468@mail.muni.cz
 */

@Entity
public class Service {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;
    
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;
    
    @NotNull
    private TypeOfServiceEnum serviceType;
    
    @NotNull
    private Duration processingTime;
  
    public Service() {
    }
    
    public Service(String name, TypeOfServiceEnum serviceType) {
        this.name = name;
        this.serviceType = serviceType;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public TypeOfServiceEnum getServiceType() {
        return serviceType;
    }

    public void setServiceType(TypeOfServiceEnum serviceType) {
        this.serviceType = serviceType;
    }
        
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Duration getProcessingTime(){
        return processingTime;
    }
    
    public void setProcessingTime(Duration time){
        this.processingTime= time;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Service)) return false;
        Service service = (Service) obj;
        return Objects.equals(id, service.id) &&
                Objects.equals(name, service.name) &&
                Objects.equals(serviceType, service.serviceType) &&
                Objects.equals(price, service.price) &&
                Objects.equals(processingTime, service.processingTime);
    }   
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, serviceType, price, processingTime);
    }
    
    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", price=" + price +
                ", processingTime=" + processingTime +
                '}';
    }
}
