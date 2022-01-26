package com.test.swagger.validation;

import com.test.swagger.dto.DictionaryItem;
import com.test.swagger.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.util.StringUtils.hasText;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, DictionaryItem> {

    private List<String> acceptedValues;
    private String errorMessage;

    @Autowired
    private TestService testService;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.value().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
        errorMessage = "value must be one of [" + String.join(", ", acceptedValues) + "]" + testService.getTemp();
    }

    @Override
    public boolean isValid(DictionaryItem enumVal, ConstraintValidatorContext context) {
        if (enumVal == null || !hasText(enumVal.getValue()) ) {
            return true;
        }

        var isValid = acceptedValues.contains(enumVal.getValue());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();
        }

        return isValid;

    }
}

