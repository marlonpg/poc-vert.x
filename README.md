# Vert.x POC

## What is Vert.x?

Vert.x is a reactive toolkit for building high-performance, scalable applications on the JVM. It's event-driven, non-blocking, and polyglot (supports multiple languages).

## Key Features

- **Event Loop**: Single-threaded event loop per CPU core
- **Non-blocking I/O**: Handles thousands of concurrent connections efficiently  
- **Reactive**: Built on reactive streams and backpressure
- **Polyglot**: Java, Kotlin, Scala, JavaScript, Ruby, Groovy support

## This POC Implementation

This POC implements the complete Vert.x tutorial with:

### 1. HelloVerticle
- Basic verticle extending AbstractVerticle
- Demonstrates start() and stop() lifecycle methods
- Uses logging to show verticle deployment

### 2. SimpleServerVerticle  
- HTTP server on port 9090
- Simple request handler returning "Welcome to Vert.x Intro"
- Demonstrates basic HTTP server creation

### 3. RestfulWebServiceVerticle
- RESTful API on port 8080
- Route: `GET /api/baeldung/articles/article/:id`
- Returns JSON Article object with path parameter
- Uses Router and RoutingContext for web handling

### 4. Article Model
- Simple POJO representing an article
- Fields: id, title, author, datePublished, numOfWords

### Architecture

```
MainVerticle (Deployment) → HelloVerticle, SimpleServerVerticle, RestfulWebServiceVerticle
                         ↓
                    Article (Domain Model)
```

## Studying notes

### Event Loop Model
Unlike traditional thread-per-request models, Vert.x uses a small number of event loops (typically one per CPU core). Each event loop handles multiple connections concurrently without blocking.

### Verticles
Verticles are the basic deployment unit in Vert.x. They're lightweight, isolated units of code. This POC demonstrates multiple verticles:
- HelloVerticle: Basic lifecycle demonstration
- SimpleServerVerticle: HTTP server
- RestfulWebServiceVerticle: RESTful web service

### Non-blocking Nature
All Vert.x APIs are non-blocking by default. Operations return `Future` objects, enabling reactive programming patterns without blocking threads.

### Event Bus
The nerve system of Vert.x applications. Verticles communicate through the event bus using asynchronous message passing.

## Running

### Development
```bash
mvnw.cmd clean compile exec:java -Dexec.mainClass="com.poc.vertx.starter.MainVerticle"
```

### Testing
```bash
mvnw.cmd clean test
```

### Packaging
```bash
mvnw.cmd clean package
```

### Running Packaged Application
```bash
java -jar target/starter-1.0.0-SNAPSHOT-app.jar
```

## Endpoints

- **SimpleServerVerticle**: http://localhost:9090/ - Returns "Welcome to Vert.x Intro"
- **RestfulWebServiceVerticle**: http://localhost:8080/api/baeldung/articles/article/{id} - Returns JSON article

## Testing

The POC includes comprehensive tests using vertx-unit:
- SimpleServerVerticleTest: Tests HTTP server response
- RestfulWebServiceVerticleTest: Tests RESTful API with path parameters

Both tests demonstrate asynchronous testing patterns with TestContext and Async.