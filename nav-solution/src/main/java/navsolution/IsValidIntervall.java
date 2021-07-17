package navsolution;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsValidIntervallValidator.class)
public @interface IsValidIntervall {


    String message() default "Invalid Intervall";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
