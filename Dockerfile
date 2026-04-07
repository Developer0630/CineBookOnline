# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Vì đã ở trong Root Directory là rapchieuphim, nên copy trực tiếp
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-slim
WORKDIR /app
# Chú ý: Tên file jar phải khớp với artifactId trong pom.xml
COPY --from=build /app/target/rapchieuphim-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]