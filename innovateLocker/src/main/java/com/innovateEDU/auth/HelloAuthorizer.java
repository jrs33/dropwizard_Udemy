package com.innovateEDU.auth;

import io.dropwizard.auth.Authorizer;
import com.innovateEDU.core.User;

public class HelloAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {

        return user.getName().equals("good-guy") && role.equals("ADMIN");

    }

}
