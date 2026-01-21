package com.poc.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class SimpleServerVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> promise) {
        vertx.createHttpServer()
            .requestHandler(r -> r.response().end("Welcome to Vert.x Intro"))
            .listen(config().getInteger("http.port", 9090), 
                result -> {
                    if (result.succeeded()) {
                        promise.complete();
                    } else {
                        promise.fail(result.cause());
                    }
                });
    }
}