package com.logicify.d2g.utils;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.exceptions.D2GBaseExceptionCodes;
import com.logicify.d2g.exceptions.NewD2GBaseExceptionCodes;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jadencorr on 31.03.17.
 */
public class DtoValidator {

    private static ValidatorFactory vf = Validation.buildDefaultValidatorFactory();

    public static void validate(IncomingDto incomingDto) throws D2GBaseException {
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<IncomingDto>> constraintViolations = validator.validate(incomingDto);
        if (!constraintViolations.isEmpty()) {
            throw new D2GBaseException(NewD2GBaseExceptionCodes.valueOf(constraintViolations.iterator().next().getMessage()));
        }
    }
}
