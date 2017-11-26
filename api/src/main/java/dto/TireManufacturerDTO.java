
package dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public class TireManufacturerDTO {

    private long id;

    private String name;

    public TireManufacturerDTO() {}

    public TireManufacturerDTO(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
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
        if (!(obj instanceof TireManufacturerDTO)) {
            return false;
        }
        final TireManufacturerDTO other = (TireManufacturerDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Manufacturer{" + "id=" + id + ", name=" + name + '}';
    }
 
}
