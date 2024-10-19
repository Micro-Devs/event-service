#FROM openjdk:17-jdk-slim
#LABEL maintainer='MUHAMMET EMRE VATAN'
#ARG JAR_FILE=target/event-service-0.0.1-SNAPSHOT.jar
#WORKDIR /opt/app
#COPY ${JAR_FILE} app.jar
#EXPOSE 9001
#ENTRYPOINT ["java", "-jar", "app.jar"]
FROM maven:3.8.4-openjdk-17 AS build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17

COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar

EXPOSE 9001

ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]