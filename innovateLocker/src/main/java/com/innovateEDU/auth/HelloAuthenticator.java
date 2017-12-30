package com.innovateEDU.auth;

import com.innovateEDU.core.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import com.google.common.base.Optional; // Guava data structure

public class HelloAuthenticator implements Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {

        // TODO: get rid of hardcoded inplementation
        if("p@ssw0rd".equals(credentials.getPassword())) {
            return Optional.of(new User());
        } else {
            return Optional.absent();
        }

    }

}
