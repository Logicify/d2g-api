package com.logicify.d2g.dtos.domain.dtosvalidation.classes;

import com.logicify.d2g.dtos.domain.dtosvalidation.annotation.Enum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by twilight on 06.06.17.
 */
public class EnumValueValidate implements ConstraintValidator<Enum,String> {

    private Enum annotation;

    @Override
    public void initialize(Enum anEnum) {
        this.annotation=anEnum;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();

        if (enumValues != null){
            for (Object enumValue:enumValues){
                if (value.equals(enumValue.toString())){
                    return true;
                }
            }
        }

        return false;
    }
}
