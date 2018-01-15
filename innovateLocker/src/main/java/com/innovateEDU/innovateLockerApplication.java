package com.innovateEDU;

import com.innovateEDU.auth.DBAuthenticator;
import com.innovateEDU.db.BookmarkDAO;
import com.innovateEDU.db.UserDAO;
import com.innovateEDU.resources.BookmarksResource;
import io.dropwizard.Application;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.innovateEDU.resources.HelloResource;
import com.innovateEDU.auth.HelloAuthenticator;
import com.innovateEDU.auth.HelloAuthorizer;
import com.innovateEDU.core.User;
import com.innovateEDU.core.Bookmark;

public class innovateLockerApplication extends Application<innovateLockerConfiguration> {

    private final HibernateBundle<innovateLockerConfiguration> hibernateBundle
            = new HibernateBundle<innovateLockerConfiguration>(User.class, Bookmark.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(innovateLockerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new innovateLockerApplication().run(args);
    }

    @Override
    public String getName() {
        return "innovateLocker";
    }

    @Override
    public void initialize(final Bootstrap<innovateLockerConfiguration> bootstrap) {
        // Used to bundle liquibase db refactoring
        bootstrap.addBundle(new MigrationsBundle<innovateLockerConfiguration>() {

            @Override
            public DataSourceFactory getDataSourceFactory(innovateLockerConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final innovateLockerConfiguration configuration,
                    final Environment environment) {

        final UserDAO userDAO
                = new UserDAO(hibernateBundle.getSessionFactory());

        final BookmarkDAO bookmarkDAO
                = new BookmarkDAO(hibernateBundle.getSessionFactory());

        // database backed resource
        environment.jersey().register(
                new BookmarksResource(bookmarkDAO)
        );

        environment.jersey().register(
                new HelloResource()
        );

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new DBAuthenticator(userDAO))
                        .setAuthorizer(new HelloAuthorizer())
                        .setRealm("SECURITY REALM")
                        .buildAuthFilter()
        ));
        
        environment.jersey().register(RolesAllowedDynamicFeature.class);

    }

}
