/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pneuservis.backend.entity.tire;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public enum TireVehicleType {
    P("Passenger Car"),
    LT("Light Truck"),
    ST("Special Trailer"),
    T("Temporary");
    
    private final String id;
    TireVehicleType(String id){this.id = id;}
    public String getValue() {return id;} 
}
