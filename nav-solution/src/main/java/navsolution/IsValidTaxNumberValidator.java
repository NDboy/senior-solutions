package navsolution;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsValidTaxNumberValidator implements ConstraintValidator<IsValidTaxNumber, String> {

    TaxNumberValidator pojo = new TaxNumberValidator();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return pojo.check(value);
        } catch (IllegalArgumentException iae) {
            return false;
        }




    }
}
