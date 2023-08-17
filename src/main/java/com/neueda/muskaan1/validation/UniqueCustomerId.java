package com.neueda.muskaan1.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueCustomerIdValidator.class)
@Documented
public @interface UniqueCustomerId {

    String message() default "CustomerId must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
