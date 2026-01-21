package com.poc.vertx.starter;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class SimpleServerVerticleTest {

    private Vertx vertx;
    private int port = 9090;

    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(SimpleServerVerticle.class.getName(), 
            testContext.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void whenReceivedResponse_thenSuccess(TestContext testContext) {
        Async async = testContext.async();

        vertx.createHttpClient()
            .getNow(port, "localhost", "/", response -> {
                response.handler(responseBody -> {
                    testContext.assertTrue(responseBody.toString().contains("Welcome"));
                    async.complete();
                });
            });
    }
}