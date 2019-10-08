package com.epam.brest.summer.courses2019.web_app.validators;

import com.epam.brest.summer.courses2019.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    private static final int ITEM_NAME_MAX_SIZE = 255;

    @Override
    public boolean supports(Class<?> aClass) {
        return Item.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "itemName", "itemName.isEmpty");
        ValidationUtils.rejectIfEmpty(errors, "itemPrice", "itemPrice.isEmpty");
        Item item = (Item)target;
        if(StringUtils.hasLength(item.getItemName()) && item.getItemName().length()>ITEM_NAME_MAX_SIZE){
            errors.rejectValue("itemName", "itemName.maxSize255");
        }

        if(item.getItemPrice()!=null && item.getItemPrice().floatValue()<0){
            errors.rejectValue("itemPrice", "itemPrice.negative");
        }
    }
}
