# Build stage
FROM maven:3.8.6-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM dockette/openjdk8
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 12058
ENTRYPOINT ["java", "-jar", "app.jar"]
