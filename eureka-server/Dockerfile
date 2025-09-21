# Stage 1: Build the application
FROM gradle:8.5-jdk17 AS builder
WORKDIR /home/gradle/project
COPY . .
RUN ./gradlew :eureka-server:bootJar --no-daemon

# Stage 2: Create the final, slim image
FROM eclipse-temurin:17-jre-jammy
COPY --from=builder /home/gradle/project/eureka-server/build/libs/eureka-server-*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app.jar"]