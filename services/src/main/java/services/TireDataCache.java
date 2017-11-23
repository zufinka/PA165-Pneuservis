package services;

import cz.muni.fi.pa165.pneuservis.backend.entity.TireManufacturer;
import cz.muni.fi.pa165.pneuservis.backend.entity.TireProperties;

import java.util.Set;
/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public class TireDataCache {

    private static Set<TireProperties> allTireProperties;
    private static Set<TireManufacturer> allTireManufacturers;

    public Set<TireProperties> getAllTireProperties() {
        return allTireProperties;
    }

    public void setAllTireProperties(Set<TireProperties> allTireProperties) {
        this.allTireProperties = allTireProperties;
    }

    public Set<TireManufacturer> getAllTireManufacturers() {
        return allTireManufacturers;
    }

    public void setAllTireManufacturers(Set<TireManufacturer> allTireManufacturers) {
        this.allTireManufacturers = allTireManufacturers;
    }
}
