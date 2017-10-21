/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pneuservis.backend.entity.Tire;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Column(nullable=false,unique=true)
    private long internal_id;
    
    @NotNull
    @Enumerated
    private TireVehicleType tireVehicleType;
    
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
    private SpeedClass speedClass;
    
    @NotNull
    @Enumerated
    private Season season;
    
    
    public TireProperties() {}

    public TireProperties(TireVehicleType tireVehicleType, int width, int aspectRatio, int diameter, int loadIndex, SpeedClass speedClass, Season season) {
        this.tireVehicleType = tireVehicleType;
        this.width = width;
        this.aspectRatio = aspectRatio;
        this.diameter = diameter;
        this.loadIndex = loadIndex;
        this.speedClass = speedClass;
        this.season = season;
        this.internal_id = this.hashCode();
    }

    public long getId() {
        return id;
    }
    
    public long getInternal_id() {
        return internal_id;
    }
        
    public TireVehicleType getTireVehicleType() {
        return tireVehicleType;
    }

    public void setTireVehicleType(TireVehicleType tireVehicleType) {
        this.tireVehicleType = tireVehicleType;
        this.internal_id = this.hashCode();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("cannot be negative");
        }
        this.width = width;
        this.internal_id = this.hashCode();
    }

    public int getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(int aspectRatio) {
        if (aspectRatio < 0) {
            throw new IllegalArgumentException("cannot be negative");
        }
        this.aspectRatio = aspectRatio;
        this.internal_id = this.hashCode();
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        if (diameter < 0) {
            throw new IllegalArgumentException("cannot be negative");
        }
        this.diameter = diameter;
        this.internal_id = this.hashCode();
    }

    public int getLoadIndex() {
        return loadIndex;
    }

    public void setLoadIndex(int loadIndex) {
        if (loadIndex < 0) {
            throw new IllegalArgumentException("cannot be negative");
        }
        this.loadIndex = loadIndex;
        this.internal_id = this.hashCode();
    }

    public SpeedClass getSpeedClass() {
        return speedClass;
    }

    public void setSpeedClass(SpeedClass speedClass) {
        this.speedClass = speedClass;
        this.internal_id = this.hashCode();
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
        this.internal_id = this.hashCode();
    }

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
        if (getClass() != obj.getClass()) {
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
