package dto;


import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;

/**
 * @author Róbert Ivan , 461468@mail.muni.cz
 */
public class ServiceDTO {
    private Long id;
   
    private String name;
    
    private BigDecimal price;
    
    private Duration processingTime;
    
    public ServiceDTO() {
    }
    
    public ServiceDTO(String name, BigDecimal price, Duration processingTime) {
        this.name = name;
        this.price = price;
        this.processingTime=processingTime;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(obj instanceof ServiceDTO)) return false;
        ServiceDTO service = (ServiceDTO) obj;
        return Objects.equals(id, service.id) &&
                Objects.equals(name, service.name) &&
                Objects.equals(price, service.price) &&
                Objects.equals(processingTime, service.processingTime);
    }   
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, processingTime);
    }
    
    @Override
    public String toString() {
        return "ServiceDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", processingTime=" + processingTime +
                '}';
    }
    
}
