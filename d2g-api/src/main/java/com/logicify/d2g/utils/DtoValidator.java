package com.logicify.d2g.utils;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.models.exceptions.D2GBaseException;
import com.logicify.d2g.models.exceptions.D2GBaseExceptionCodes;
import com.sun.deploy.util.StringUtils;

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
        Set<ConstraintViolation<IncomingDto>> constraintViolations = validator
                .validate(incomingDto);
        if (!constraintViolations.isEmpty()) {
            Set<String> violationMessages = new HashSet<>();
            for (ConstraintViolation<IncomingDto> constraintViolation : constraintViolations) {
                violationMessages.add(String.format("%s: %s", constraintViolation.getPropertyPath(), constraintViolation.getMessage()));
            }
            throw new D2GBaseException(D2GBaseExceptionCodes.WRONG_DATA, String.format("Data is not valid: %s", StringUtils.join(violationMessages, ", ")));
        }
    }
}
