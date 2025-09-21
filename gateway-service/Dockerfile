# Stage 1: Build the application
FROM gradle:8.5-jdk17 AS builder
WORKDIR /home/gradle/project
COPY . .
RUN ./gradlew :gateway-service:bootJar --no-daemon

# Stage 2: Create the final, slim image
FROM eclipse-temurin:17-jre-jammy
COPY --from=builder /home/gradle/project/gateway-service/build/libs/gateway-service-*.jar app.jar
EXPOSE 4004
ENTRYPOINT ["java","-jar","/app.jar"]