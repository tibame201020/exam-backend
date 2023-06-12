FROM openjdk:8-jdk-alpine
COPY ./target/exams_backend-0.0.1-SNAPSHOT.jar /app/backend.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "backend.jar"]
