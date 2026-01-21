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
public class RestfulWebServiceVerticleTest {

    private Vertx vertx;

    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(RestfulWebServiceVerticle.class.getName(), 
            testContext.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void givenId_whenReceivedArticle_thenSuccess(TestContext testContext) {
        Async async = testContext.async();

        vertx.createHttpClient()
            .getNow(8080, "localhost", "/api/baeldung/articles/article/12345", 
                response -> {
                    response.handler(responseBody -> {
                        testContext.assertTrue(
                            responseBody.toString().contains("\"id\" : \"12345\""));
                        async.complete();
                    });
                });
    }
}