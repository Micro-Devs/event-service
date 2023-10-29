FROM openjdk:17-jdk-slim
LABEL maintainer='MUHAMMET EMRE VATAN'
ARG JAR_FILE=target/event-service-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "app.jar"]