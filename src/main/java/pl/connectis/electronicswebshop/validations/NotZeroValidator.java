package pl.connectis.electronicswebshop.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotZeroValidator implements ConstraintValidator<NotZero, Integer> {
    @Override
    public void initialize(NotZero valueToResearch) {

    }

    @Override
    public boolean isValid(Integer valueField, ConstraintValidatorContext constraintValidatorContext) {
        return valueField == 0;
    }
}
