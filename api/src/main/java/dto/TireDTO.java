package dto;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */

public class TireDTO {
      

    private long id;

    private String name;

    private TireManufacturerDTO manufacturer;

    private TirePropertiesDTO tirePropertiesDTO;

    private int onStock;

    private BigDecimal price;
    
    private String imageUrl;
    
    private String description;

    public TireDTO(){}

    public TireDTO(String name, TireManufacturerDTO manufacturer, TirePropertiesDTO tirePropertiesDTO, int onStock, BigDecimal price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.tirePropertiesDTO = tirePropertiesDTO;
        this.onStock = onStock;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setManufacturer(TireManufacturerDTO manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
            
    public TireManufacturerDTO getTireManufacturer() {
        return manufacturer;
    }

    public void setTireManufacturer(TireManufacturerDTO manufacturer) {
        this.manufacturer = manufacturer;
    }

    public TirePropertiesDTO getTirePropertiesDTO() {
        return tirePropertiesDTO;
    }

    public void setTirePropertiesDTO(TirePropertiesDTO tirePropertiesDTO) {
        this.tirePropertiesDTO = tirePropertiesDTO;
        
    }

    public int getOnStock() {
        return onStock;
    }

    public void setOnStock(int onStock) {
        this.onStock = onStock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage_url() {
        return imageUrl;
    }

    public void setImage_url(String image_url) {
        this.imageUrl = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.manufacturer);
        hash = 79 * hash + Objects.hashCode(this.tirePropertiesDTO);
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
        if (! (obj instanceof TireDTO) ) {
            return false;
        }
        final TireDTO other = (TireDTO) obj;
        if (this.onStock != other.onStock) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.tirePropertiesDTO, other.tirePropertiesDTO)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TireDTO{" + "manufacturer=" + manufacturer + ", tirePropertiesDTO=" + tirePropertiesDTO + ", onStock=" + onStock + ", price=" + price + '}';
    }
    
    
    
}
