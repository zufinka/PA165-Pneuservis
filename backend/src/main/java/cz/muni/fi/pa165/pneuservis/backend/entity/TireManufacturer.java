
package cz.muni.fi.pa165.pneuservis.backend.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
@Entity
public class TireManufacturer {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Column(nullable=false,unique=true)
    private String name;

    public TireManufacturer() {}

    public long getId() {
        return id;
    }
    
    public TireManufacturer(String name) {
        this.name = name;
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
        if (!(obj instanceof TireManufacturer)) {
            return false;
        }
        final TireManufacturer other = (TireManufacturer) obj;
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
