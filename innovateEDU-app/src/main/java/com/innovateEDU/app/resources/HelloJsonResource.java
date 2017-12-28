package com.innovateEDU.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.innovateEDU.app.core.Greeting;

@Path("/hellojson")
public class HelloJsonResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting getJSONGreeting() {

        return new Greeting("Hello World!");

    }

}
