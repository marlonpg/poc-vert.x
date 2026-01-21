@echo off
set JAVA_HOME=C:\Users\gamba\.jdks\corretto-25.0.1
set PATH=%JAVA_HOME%\bin;%PATH%
mvnw.cmd clean compile exec:java -Dexec.mainClass="com.poc.vertx.starter.MainVerticle"