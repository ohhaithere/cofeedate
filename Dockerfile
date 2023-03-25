FROM --platform=linux/amd64 adoptopenjdk/openjdk11:latest

COPY target/coffeedate-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
