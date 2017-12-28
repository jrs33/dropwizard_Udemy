package com.innovateEDU.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.innovateEDU.app.resources.HelloResource;
import com.innovateEDU.app.resources.HelloJsonResource;
import com.innovateEDU.app.health.HelloResourceTest;

public class innovateEDUApplication extends Application<innovateEDUConfiguration> {

    public static void main( String[] args ) throws Exception {

        new innovateEDUApplication().run(args);

    }

    @Override
    public void run(innovateEDUConfiguration configuration,
                    Environment environment) {

        // Run Health Checks
        final HelloResourceTest healthCheck = new HelloResourceTest();

        // Register Health Checks in environment
        environment.healthChecks().register("hello", healthCheck);

        // Register Jersey resources in Environment
        environment.jersey().register(new HelloResource());
        environment.jersey().register(new HelloJsonResource());

    }

}
