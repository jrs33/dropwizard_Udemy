package com.innovateEDU.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.innovateEDU.app.resources.HelloResource;

public class innovateEDUApplication extends Application<innovateEDUConfiguration> {

    public static void main( String[] args ) throws Exception {

        new innovateEDUApplication().run(args);

    }

    @Override
    public void run(innovateEDUConfiguration configuration,
                    Environment environment) {

        // Register Jersey resources in Environment
        environment.jersey().register(new HelloResource());

    }

}
