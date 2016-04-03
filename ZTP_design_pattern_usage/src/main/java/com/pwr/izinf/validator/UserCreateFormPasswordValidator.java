package com.pwr.izinf.validator;

import com.pwr.izinf.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserCreateFormPasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User form = (User) target;
        if (!form.getPassword().equals(form.getPassword())) {
            errors.rejectValue("password2", "user.error.password.no_match");
        }
    }
}
