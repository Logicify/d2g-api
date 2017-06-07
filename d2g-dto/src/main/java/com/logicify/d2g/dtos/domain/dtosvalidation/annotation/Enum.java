package com.logicify.d2g.dtos.domain.dtosvalidation.annotation;

import com.logicify.d2g.dtos.domain.dtosvalidation.classes.EnumValueValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by twilight on 06.06.17.
 */

@Documented
@Constraint(validatedBy = {EnumValueValidate.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {

    String message() default "Invalid value. This is not permitted.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends java.lang.Enum<?>> enumClass();
}
