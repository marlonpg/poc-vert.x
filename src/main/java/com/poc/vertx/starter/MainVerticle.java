package com.poc.vertx.starter;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.VerticleBase;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class MainVerticle extends VerticleBase {

  private final UserService userService = new UserService();

  @Override
  public Future<?> start() {
    // Create a Router
    Router router = Router.router(vertx);

    router.route(HttpMethod.POST, "/user/create").handler(context -> {
      JsonObject body = context.body().asJsonObject();
      
      try {
        UserModel user = userService.createUser(
          body.getString("name"), 
          body.getString("email")
        );
        context.response().setStatusCode(201);
        context.json(user);
      } catch (IllegalArgumentException e) {
        context.response().setStatusCode(400).end(new JsonObject().put("error", e.getMessage()).encode());
      }
    });

    router.route("/health").handler(context -> {
      // Get the address of the request
      String address = context.request().connection().remoteAddress().toString();
      // Get the query parameter "name"
      MultiMap queryParams = context.queryParams();
      String name = queryParams.contains("name") ? queryParams.get("name") : "unknown";
      // Write a json response
      context.json(
        new JsonObject()
          .put("status", name)
          .put("address", address)
          .put("message", "Hello " + name + " connected from " + address)
      );
    });

    // Create the HTTP server
    return vertx.createHttpServer()
      // Handle every request using the router
      .requestHandler(router)
      // Start listening
      .listen(8888)
      // Print the port on success
      .onSuccess(server -> {
        System.out.println("HTTP server started on port " + server.actualPort());
      })
      // Print the problem on failure
      .onFailure(throwable -> {
        throwable.printStackTrace();
      });
  }
}
