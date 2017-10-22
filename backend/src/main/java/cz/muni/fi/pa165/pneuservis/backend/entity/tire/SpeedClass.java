/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pneuservis.backend.entity.tire;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public enum SpeedClass {
    B(50),
    C(60),
    D(65),
    E(70),
    F(80),
    G(90),
    J(100),
    K(110),
    L(120),
    M(130),
    N(140),
    P(150),
    Q(160),
    R(170),
    S(180),
    T(190),
    U(200),
    H(210),
    V(240),
    W(270),
    Y(300);
    
    private final int speed;
    SpeedClass(int speed){this.speed = speed;}
    public int getValue() {return speed;} 
}
