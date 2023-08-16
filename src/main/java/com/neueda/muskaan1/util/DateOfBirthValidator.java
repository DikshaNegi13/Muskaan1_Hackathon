package com.neueda.muskaan1.util;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, String> {

    private static final int MIN_YEAR = 1900; // Define the minimum allowable year

    @Override
    public boolean isValid(String dob, ConstraintValidatorContext context) {
        if (dob == null) {
            return true; // Allow null values
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            Date dobDate = dateFormat.parse(dob);
            Date currentDate = new Date();

            Calendar cal = Calendar.getInstance();
            cal.setTime(dobDate);
            int year = cal.get(Calendar.YEAR);

            return dobDate.before(currentDate) && year >= MIN_YEAR;
        } catch (ParseException e) {
            return false;
        }
    }
}

