/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pneuservis.backend.entity.tire;

import cz.muni.fi.pa165.pneuservis.backend.enums.SeasonEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.TireVehicleTypeEnum;
import cz.muni.fi.pa165.pneuservis.backend.enums.SpeedClassEnum;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
@Entity
public class TireProperties {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Enumerated
    private TireVehicleTypeEnum tireVehicleType;
    
    @NotNull
    private int width;
    
    @NotNull
    private int aspectRatio;
    
    @NotNull
    private int diameter;
    
    @NotNull
    private int loadIndex;
    
    @NotNull
    @Enumerated
    private SpeedClassEnum speedClass;
    
    @NotNull
    @Enumerated
    private SeasonEnum season;
    
    /*
    @OneToMany
    private Set<Tire> tires = new HashSet<>();
    */

    public TireProperties() {}

    public TireProperties(TireVehicleTypeEnum tireVehicleType, int width, int aspectRatio, int diameter, int loadIndex, SpeedClassEnum speedClass, SeasonEnum season) {
        this.tireVehicleType = tireVehicleType;
        this.width = width;
        this.aspectRatio = aspectRatio;
        this.diameter = diameter;
        this.loadIndex = loadIndex;
        this.speedClass = speedClass;
        this.season = season;
    }

    public long getId() {
        return id;
    }
    
    public TireVehicleTypeEnum getTireVehicleType() {
        return tireVehicleType;
    }

    public void setTireVehicleType(TireVehicleTypeEnum tireVehicleType) {
        this.tireVehicleType = tireVehicleType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("cannot be negative");
        }
        this.width = width;
    }

    public int getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(int aspectRatio) {
        if (aspectRatio < 0) {
            throw new IllegalArgumentException("cannot be negative");
        }
        this.aspectRatio = aspectRatio;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        if (diameter < 0) {
            throw new IllegalArgumentException("cannot be negative");
        }
        this.diameter = diameter;
    }

    public int getLoadIndex() {
        return loadIndex;
    }

    public void setLoadIndex(int loadIndex) {
        if (loadIndex < 0) {
            throw new IllegalArgumentException("cannot be negative");
        }
        this.loadIndex = loadIndex;
    }

    public SpeedClassEnum getSpeedClass() {
        return speedClass;
    }

    public void setSpeedClass(SpeedClassEnum speedClass) {
        this.speedClass = speedClass;
    }

    public SeasonEnum getSeason() {
        return season;
    }

    public void setSeason(SeasonEnum season) {
        this.season = season;
    }
    
    /*
    public void addTire(Tire t){
        tires.add(t);
    }
    
    public void removeTire(Tire t){
        tires.remove(t);
    }

    public Set<Tire> getTires() {
        return Collections.unmodifiableSet(tires);
    }

    public void setTires(Set<Tire> tires) {
        this.tires = tires;
    }
    */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.tireVehicleType);
        hash = 97 * hash + this.width;
        hash = 97 * hash + this.aspectRatio;
        hash = 97 * hash + this.diameter;
        hash = 97 * hash + this.loadIndex;
        hash = 97 * hash + Objects.hashCode(this.speedClass);
        hash = 97 * hash + Objects.hashCode(this.season);
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
        if (!(obj instanceof TireProperties)) {
            return false;
        }
        final TireProperties other = (TireProperties) obj;
        if (this.width != other.width) {
            return false;
        }
        if (this.aspectRatio != other.aspectRatio) {
            return false;
        }
        if (this.diameter != other.diameter) {
            return false;
        }
        if (this.loadIndex != other.loadIndex) {
            return false;
        }
        if (this.tireVehicleType != other.tireVehicleType) {
            return false;
        }
        if (this.speedClass != other.speedClass) {
            return false;
        }
        if (this.season != other.season) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TireProperties{" + "tireVehicleType=" + tireVehicleType + ", width=" + width + ", aspectRatio=" + aspectRatio + ", diameter=" + diameter + ", loadIndex=" + loadIndex + ", speedClass=" + speedClass + ", season=" + season + '}';
    }

}
