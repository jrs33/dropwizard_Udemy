package com.innovateEDU.auth;

import com.innovateEDU.core.User;
import com.innovateEDU.db.UserDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import com.google.common.base.Optional;

public class DBAuthenticator
        implements Authenticator<BasicCredentials, User> {

    private UserDAO userDAO;

    public DBAuthenticator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {

        return userDAO.findUsernameByPassword(
                credentials.getUsername(),
                credentials.getPassword()
        );

    }

}
