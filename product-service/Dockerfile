# Stage 1: Build the application
FROM gradle:8.5-jdk17 AS builder
WORKDIR /home/gradle/project
COPY . .
RUN ./gradlew :product-service:bootJar --no-daemon

# Stage 2: Create the final, slim image
FROM openjdk:17-jdk-slim
COPY --from=builder /home/gradle/project/product-service/build/libs/product-service-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
