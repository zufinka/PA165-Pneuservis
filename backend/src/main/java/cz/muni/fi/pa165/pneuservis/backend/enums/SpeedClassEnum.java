
package cz.muni.fi.pa165.pneuservis.backend.enums;

/***
 * @author Jakub Palenik, 422453@mail.muni.cz
 */
public enum SpeedClassEnum {
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
    SpeedClassEnum(int speed){this.speed = speed;}
    public int getValue() {return speed;} 
}
