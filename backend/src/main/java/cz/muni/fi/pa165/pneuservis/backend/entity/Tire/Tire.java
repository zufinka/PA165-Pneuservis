package cz.muni.fi.pa165.pneuservis.backend.entity.Tire;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
@Entity
public class Tire {
      
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Column(nullable=false,unique=true)
    private long internal_id;
    
    @ManyToOne
    @NotNull
    private Manufacturer manufacturer;
     
    @ManyToOne
    @NotNull
    private TireProperties tireProperties; 
    
    @NotNull
    private int onStock;
    
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;
    
    private String image_url;
    
    private String description;

    public Tire(){}

    public Tire(Manufacturer manufacturer, TireProperties tireProperties, int onStock, BigDecimal price) {
        this.manufacturer = manufacturer;
        this.tireProperties = tireProperties;
        this.onStock = onStock;
        this.price = price;
        this.internal_id = this.hashCode();
        
    }

    public long getInternal_id() {
        return internal_id;
    }
        
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        this.internal_id = this.hashCode();
    }

    public TireProperties getTireProperties() {
        return tireProperties;
    }

    public void setTireProperties(TireProperties tireProperties) {
        this.tireProperties = tireProperties;
        this.internal_id = this.hashCode();
        
    }

    public int getOnStock() {
        return onStock;
    }

    public void setOnStock(int onStock) {
        this.onStock = onStock;
        this.internal_id = this.hashCode();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.internal_id = this.hashCode();
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.manufacturer);
        hash = 79 * hash + Objects.hashCode(this.tireProperties);
        hash = 79 * hash + this.onStock;
        hash = 79 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tire other = (Tire) obj;
        if (this.onStock != other.onStock) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.tireProperties, other.tireProperties)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tire{" + "manufacturer=" + manufacturer + ", tireProperties=" + tireProperties + ", onStock=" + onStock + ", price=" + price + '}';
    }
    
    
    
}
