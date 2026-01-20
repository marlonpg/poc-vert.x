# Vert.x POC

## What is Vert.x?

Vert.x is a reactive toolkit for building high-performance, scalable applications on the JVM. It's event-driven, non-blocking, and polyglot (supports multiple languages).

## Key Features

- **Event Loop**: Single-threaded event loop per CPU core
- **Non-blocking I/O**: Handles thousands of concurrent connections efficiently  
- **Reactive**: Built on reactive streams and backpressure
- **Polyglot**: Java, Kotlin, Scala, JavaScript, Ruby, Groovy support

## This POC

Simple REST API with:
- `POST /user/create` - Creates a user with validation
- `GET /health` - Health check endpoint

### Architecture

```
MainVerticle (Router) → UserService (Business Logic) → UserModel (Domain)
```

## Studying notes

### Event Loop Model
Unlike traditional thread-per-request models, Vert.x uses a small number of event loops (typically one per CPU core). Each event loop handles multiple connections concurrently without blocking.

### Verticles
Verticles are the basic deployment unit in Vert.x. They're lightweight, isolated units of code. This POC uses a single MainVerticle that handles HTTP routing.

### Non-blocking Nature
All Vert.x APIs are non-blocking by default. Operations return `Future` objects, enabling reactive programming patterns without blocking threads.

## Running

```bash
mvn clean compile exec:java
```

Server starts on port 8888.