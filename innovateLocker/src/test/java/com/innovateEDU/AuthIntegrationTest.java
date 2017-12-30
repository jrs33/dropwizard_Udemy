package com.innovateEDU;

import io.dropwizard.testing.junit.DropwizardAppRule;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class AuthIntegrationTest {

    private static final String CONFIG_PATH
            = "config.yml";

    @ClassRule
    public static final DropwizardAppRule<innovateLockerConfiguration> RULE
            = new DropwizardAppRule<>(innovateLockerApplication.class, CONFIG_PATH);

    private static final String TARGET
            = "http://localhost:8080";

    private static final String PATH
            = "hello/secured";

    private Client client;

    // Run before every single test
    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
    }

    // Run after each test method
    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void testBadPath() {
        Response response = client
                .target(TARGET)
                .path(PATH)
                .request()
                .get();
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

}
