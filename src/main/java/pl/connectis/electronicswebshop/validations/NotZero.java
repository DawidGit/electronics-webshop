package pl.connectis.electronicswebshop.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotZeroValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotZero {

    String message() default "The value can not be 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
