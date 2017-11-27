package dto;


import com.google.common.base.MoreObjects;
import cz.muni.fi.pa165.pneuservis.backend.enums.TypeOfServiceEnum;

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

    private TypeOfServiceEnum serviceType;
    
    private Duration processingTime;
    
    public ServiceDTO() {
    }

    public ServiceDTO(Long id, String name, BigDecimal price, TypeOfServiceEnum serviceType, Duration processingTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.serviceType = serviceType;
        this.processingTime = processingTime;
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

    public TypeOfServiceEnum getServiceType() {
        return serviceType;
    }

    public void setServiceType(TypeOfServiceEnum serviceType) {
        this.serviceType = serviceType;
    }

    public Duration getProcessingTime(){
        return processingTime;
    }
    
    public void setProcessingTime(Duration time){
        this.processingTime= time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceDTO)) return false;
        ServiceDTO that = (ServiceDTO) o;
        return com.google.common.base.Objects.equal(id, that.id) &&
                com.google.common.base.Objects.equal(name, that.name) &&
                com.google.common.base.Objects.equal(price, that.price) &&
                serviceType == that.serviceType &&
                com.google.common.base.Objects.equal(processingTime, that.processingTime);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(id, name, price, serviceType, processingTime);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("price", price)
                .add("serviceType", serviceType)
                .add("processingTime", processingTime)
                .toString();
    }
}
