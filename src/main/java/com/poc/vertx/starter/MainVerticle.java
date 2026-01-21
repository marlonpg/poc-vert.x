package com.poc.vertx.starter;

import io.vertx.core.Vertx;

public class MainVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        
        // Deploy HelloVerticle
        vertx.deployVerticle(new HelloVerticle());
        
        // Deploy SimpleServerVerticle
        vertx.deployVerticle(new SimpleServerVerticle());
        
        // Deploy RestfulWebServiceVerticle
        vertx.deployVerticle(new RestfulWebServiceVerticle());
    }
}
