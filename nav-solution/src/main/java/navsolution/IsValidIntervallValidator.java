package navsolution;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class IsValidIntervallValidator implements ConstraintValidator<IsValidIntervall, Intervall> {

    @Override
    public boolean isValid(Intervall intervall, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime now = LocalDateTime.now();
        return intervall.getStartTime().isAfter(now)
                && intervall.getEndTime().isAfter(intervall.getStartTime());
    }
}
