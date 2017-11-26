package facade;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 * @see org.springframework.context.annotation.ComponentScan#basePackageClasses
 */
public final class FacadePackageMarker {
    private FacadePackageMarker() {
        throw new AssertionError("You shouldn't be here");
    }
}
