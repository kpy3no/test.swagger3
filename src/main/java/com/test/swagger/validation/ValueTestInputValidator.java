package com.test.swagger.validation;

import com.test.swagger.dto.DictionaryItem;
import com.test.swagger.dto.TestInput;
import com.test.swagger.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.util.StringUtils.hasText;

public class ValueTestInputValidator implements ConstraintValidator<ValidTestInput, TestInput> {

    private List<String> acceptedValues;
    private String errorMessage;

    @Autowired
    private TestService testService;

    @Override
    public void initialize(ValidTestInput annotation) {
        errorMessage = "more than one" + testService.getTemp();
    }

    @Override
    public boolean isValid(TestInput testInput, ConstraintValidatorContext context) {
        boolean isValid = testInput.getId() > 0;
        if (isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

