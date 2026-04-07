# Build stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Copy file cấu hình trước để cache layer
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
# Cấp quyền chạy cho mvnw
RUN chmod +x mvnw
# Copy code và build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]