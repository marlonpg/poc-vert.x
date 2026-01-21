package com.poc.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import java.util.logging.Logger;

public class HelloVerticle extends AbstractVerticle {
    
    private static final Logger LOGGER = Logger.getLogger(HelloVerticle.class.getName());

    @Override
    public void start(Promise<Void> promise) {
        LOGGER.info("Welcome to Vertx");
        promise.complete();
    }
    
    @Override
    public void stop() {
        LOGGER.info("Shutting down application");
    }
}