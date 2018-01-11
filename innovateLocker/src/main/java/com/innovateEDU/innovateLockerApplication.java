package com.innovateEDU;

import io.dropwizard.Application;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.innovateEDU.resources.HelloResource;
import com.innovateEDU.auth.HelloAuthenticator;
import com.innovateEDU.auth.HelloAuthorizer;
import com.innovateEDU.core.User;

public class innovateLockerApplication extends Application<innovateLockerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new innovateLockerApplication().run(args);
    }

    @Override
    public String getName() {
        return "innovateLocker";
    }

    @Override
    public void initialize(final Bootstrap<innovateLockerConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<innovateLockerConfiguration>() {

            @Override
            public DataSourceFactory getDataSourceFactory(innovateLockerConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
            
        });
    }

    @Override
    public void run(final innovateLockerConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(
                new HelloResource()
        );
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new HelloAuthenticator(configuration.getPassword()))
                        .setAuthorizer(new HelloAuthorizer())
                        .setRealm("SECURITY REALM")
                        .buildAuthFilter()
        ));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

    }

}
