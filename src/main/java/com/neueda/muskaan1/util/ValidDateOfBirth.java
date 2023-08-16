package com.neueda.muskaan1.util;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

    @Documented
    @Constraint(validatedBy = DateOfBirthValidator.class)
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValidDateOfBirth {
        String message() default "Invalid date of birth";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }

