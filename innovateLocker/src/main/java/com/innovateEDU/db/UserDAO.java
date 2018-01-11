package com.innovateEDU.db;

import com.innovateEDU.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<User>{

    public UserDAO(SessionFactory sessionFactory) {

        super(sessionFactory);

    }

    public List<User> findAll() {

        return list(
                namedQuery(
                        "com.innovateEDU.core.User.findAll"
                )
        );

    }

    public Optional<User> findUsernameByPassword(
            String username,
            String password
    ) {
        return Optional.ofNullable(
                uniqueResult(
                        namedQuery("com.innovateEDU.core.User.findByUsername")
                                .setParameter("username", username)
                                .setParameter("password", password)
                )
        );
    }

}
