package com.ecommerce.admin.web.validators;

import com.ecommerce.core.entity.User;
import com.ecommerce.core.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by dguzik
 */
@Component
public class UserValidator implements Validator {

    @Autowired protected MessageSource messageSource;
    @Autowired protected SecurityService securityService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String email = user.getEmail();
        User userByEmail = securityService.findUserByEmail(email);
        if (userByEmail != null) {
            errors.rejectValue("email", "error.exists", new Object[]{email}, "Email " + email + " already in use");
        }
    }
}
