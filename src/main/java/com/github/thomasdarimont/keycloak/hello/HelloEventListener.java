package com.github.thomasdarimont.keycloak.hello;

import com.google.auto.service.AutoService;
import lombok.extern.jbosslog.JBossLog;
import org.keycloak.Config;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

@JBossLog
public class HelloEventListener implements EventListenerProvider {

    @Override
    public void onEvent(Event event) {
        log.infof("Handle UserEvent. realm=%s type=%s", event.getRealmName(), event.getType());
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        log.infof("Handle AdminEvent. realm=%s operation=%s resource=%s", adminEvent.getRealmName(), adminEvent.getOperationType(), adminEvent.getResourcePath());
    }

    @Override
    public void close() {
    }

    @AutoService(EventListenerProviderFactory.class)
    public static class Factory implements EventListenerProviderFactory {

        @Override
        public String getId() {
            return "hello-event-listener";
        }

        @Override
        public EventListenerProvider create(KeycloakSession keycloakSession) {
            return new HelloEventListener();
        }

        @Override
        public void init(Config.Scope scope) {

        }

        @Override
        public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

        }

        @Override
        public void close() {

        }
    }
}
