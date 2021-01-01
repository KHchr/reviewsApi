#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/reviewsApp/src
COPY pom.xml /home/reviewsApp
RUN mvn -f /home/reviewsApp/pom.xml package spring-boot:repackage

#
# Package stage
#
FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=build /home/reviewsApp/target/reviewsApp-1.0-SNAPSHOT.jar /usr/local/lib/reviewsApp-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/reviewsApp-1.0-SNAPSHOT.jar"]