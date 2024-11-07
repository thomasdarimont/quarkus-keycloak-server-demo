package com.github.thomasdarimont.keycloak.hello;

import com.google.auto.service.AutoService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;
import lombok.extern.jbosslog.JBossLog;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

import java.time.Instant;
import java.util.Map;

/**
 * http://localhost:8080/realms/master/hello
 */
public class HelloResource implements RealmResourceProvider {

    public static final String ID = "hello";

    @Override
    public Object getResource() {
        return this;
    }

    @GET
    public Response greet() {
        return Response.ok(Map.of("message", "Hello " + Instant.now())).build();
    }

    @Override
    public void close() {

    }

    @JBossLog
    @AutoService(RealmResourceProviderFactory.class)
    public static class Factory implements RealmResourceProviderFactory {

        private static final HelloResource INSTANCE = new HelloResource();

        @Override
        public String getId() {
            return HelloResource.ID;
        }

        @Override
        public RealmResourceProvider create(KeycloakSession session) {
            return INSTANCE;
        }

        @Override
        public void init(Config.Scope config) {
            // NOOP

        }

        @Override
        public void postInit(KeycloakSessionFactory factory) {
            log.info("Initialize");
        }

        @Override
        public void close() {
            // NOOP
        }
    }
}
