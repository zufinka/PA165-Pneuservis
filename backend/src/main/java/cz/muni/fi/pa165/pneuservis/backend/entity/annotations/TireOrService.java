package cz.muni.fi.pa165.pneuservis.backend.entity.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Martin Zilak, 433372@mail.muni.cz
 */

@Documented
@Constraint(validatedBy = OrderItemValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TireOrService {
    String message() default "Tire and Service are mutually exclusive";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
