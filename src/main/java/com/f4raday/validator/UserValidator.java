package com.f4raday.validator;

import com.f4raday.model.User;
import com.f4raday.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

        if (userService.findByUsername(user.getUsername()) != null) {
            logger.debug(String.format("Trying to create a user that already exists: %s", user.getUsername()));
            errors.rejectValue("username", "UsernameExists");
        }

    }
}
