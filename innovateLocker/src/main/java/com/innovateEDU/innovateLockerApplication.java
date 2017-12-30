package com.innovateEDU;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.innovateEDU.resources.HelloResource;

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
        // TODO: application initialization
    }

    @Override
    public void run(final innovateLockerConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(
                new HelloResource()
        );
        

    }

}
