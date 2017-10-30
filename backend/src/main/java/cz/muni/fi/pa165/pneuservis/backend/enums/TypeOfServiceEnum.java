/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pneuservis.backend.enums;

/***
 * @author Róbert Ivan , 461468@mail.muni.cz
 */

public enum TypeOfServiceEnum {
    TIRECHANGE("Tire change"),
    BRAKECALIBRATION("Brake calibration"),
    CLEANING("Cleaning"),
    CONVERGANCECHECK("Convergance check"),
    OILCHANGE("Oil change"),
    MUFFLERREPAIR("Muffler repair"),
    EXHAUSTSYSTEMREPAIR("Exhaust system repair");
    
    private final String id;
    TypeOfServiceEnum(String id){this.id = id;}
    public String getValue() {return id;} 
}
