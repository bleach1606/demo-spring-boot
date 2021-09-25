FROM maven:3.8.1-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:8
ENV JAVA_OPTS="-Xms256m -Xmx1024m"
COPY --from=build /usr/src/app/target/demo-service-0.0.1-SNAPSHOT.jar /app/demo-service.jar


WORKDIR /app
