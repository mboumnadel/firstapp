package com.med.firstapp.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class RestResourceConfig extends ResourceConfig {
    public RestResourceConfig() {
        packages("com.med.firstapp.rest")
        .register(DeclarativeLinkingFeature.class);
    }
}
//Documentation : https://dennis-xlc.gitbooks.io/restful-java-with-jax-rs-2-0-2rd-edition/en/