package cz.muni.fi.pa165.pneuservis.backend.entity.annotations;

import cz.muni.fi.pa165.pneuservis.backend.entity.OrderItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrderItemValidator implements ConstraintValidator<TireOrService, OrderItem> {
    @Override
    public void initialize(TireOrService tireOrService) {

    }

    @Override
    public boolean isValid(OrderItem orderItem, ConstraintValidatorContext constraintValidatorContext) {
        return (orderItem.getTire() == null ^ orderItem.getService() == null);
    }
}
