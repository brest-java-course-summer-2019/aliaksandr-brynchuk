package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "itemsIds", "itemsIds.empty");
        Order order = (Order)target;
        if(order.getItemsIds().isEmpty()){
            errors.rejectValue("itemsIds", "itemsIds.empty");
        }
    }
}
