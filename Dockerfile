# Stage 1: Build the JAR
FROM gradle:8.7-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Stage 2: Run the app
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]