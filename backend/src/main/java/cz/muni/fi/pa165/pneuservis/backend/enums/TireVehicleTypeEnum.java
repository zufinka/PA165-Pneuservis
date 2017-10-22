
package cz.muni.fi.pa165.pneuservis.backend.enums;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public enum TireVehicleTypeEnum {
    P("Passenger Car"),
    LT("Light Truck"),
    ST("Special Trailer"),
    T("Temporary");
    
    private final String id;
    TireVehicleTypeEnum(String id){this.id = id;}
    public String getValue() {return id;} 
}
