package com.neueda.muskaan1.validation;
import com.neueda.muskaan1.service.CustomerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCustomerIdValidator implements ConstraintValidator<UniqueCustomerId, Integer> {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean isValid(Integer customerId, ConstraintValidatorContext context) {
        if (customerId == null) {
            return true; // Allow null values (optional validation)
        }
        return !customerService.existsByCustomerId(customerId);
    }

}
