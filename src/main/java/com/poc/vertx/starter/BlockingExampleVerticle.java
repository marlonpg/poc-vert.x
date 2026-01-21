package com.poc.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class BlockingExampleVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> promise) {
        Router router = Router.router(vertx);

        router.get("/fast").handler(this::fastHandler);
        router.get("/blocking").handler(this::blockingHandler);
        router.put("/nonblocking").handler(this::nonBlockingHandler);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        promise.complete();
                    } else {
                        promise.fail(result.cause());
                    }
                });
    }

    private void fastHandler(RoutingContext ctx) {
        ctx.response().end("Fast response");
    }

    // We should never block the main thread (event loop)
    private void blockingHandler(RoutingContext ctx) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        ctx.response().end("Blocking response");
    }

    private void nonBlockingHandler(RoutingContext ctx) {
        vertx.executeBlocking(promise -> {
            try {
                Thread.sleep(5000); // This runs on a worker thread
                // it is like we usually do in WebFlux
                //Mono.fromCallable(() -> blockingOperation()).subscribeOn(Schedulers.boundedElastic())
                promise.complete("Slow non-blocking response");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                promise.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                ctx.response().end(result.result().toString());
            } else {
                ctx.response().setStatusCode(500).end("Error");
            }
        });
    }
}