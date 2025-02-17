
FROM maven:3.9.7-amazoncorretto-21 AS build

COPY pom.xml ./
COPY .mvn .mvn
COPY src src

RUN mvn clean install -DskipTests

FROM amazoncorretto:21

WORKDIR userservice

COPY --from=build target/*.jar userservice.jar

EXPOSE 1113

ENTRYPOINT ["java", "-jar", "userservice.jar"]