#!/bin/bash
export JAVA_HOME="C:/Users/gamba/.jdks/corretto-25.0.1"
export PATH="$JAVA_HOME/bin:$PATH"
./mvnw clean compile exec:java -Dexec.mainClass="com.poc.vertx.starter.MainVerticle"