# Stage 1: Build bằng Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Chạy bằng JRE (Dùng Eclipse Temurin thay vì OpenJDK cũ)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy file jar từ bước build sang
COPY --from=build /app/target/rapchieuphim-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]