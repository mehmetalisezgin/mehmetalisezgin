# Use a multi‑stage build so that the final image contains only the
# compiled application and a JRE. This reduces image size and speeds up
# deployment. The build stage uses the Maven image to compile and package
# the Spring Boot application into a self‑contained jar file.

## Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only the Maven descriptor first to leverage Docker layer caching.
COPY pom.xml ./
# Create the empty source directory tree so that the first build does not fail.
RUN mkdir -p src/main/java src/main/resources
# Download all dependencies. This happens during the first build and will
# remain cached in subsequent builds unless pom.xml changes.
RUN mvn -B dependency:go-offline

# Copy the actual source code.
COPY src ./src

# Package the application. The -DskipTests flag is set because there are no
# unit tests in this project. The resulting jar is placed under target/.
RUN mvn -B package -DskipTests

## Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the packaged jar from the build stage into the runtime image.
COPY --from=build /app/target/resume-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port. Render will pick this up
EXPOSE 8080

# Define the startup command. Spring Boot will listen on port 8080 by default.
ENTRYPOINT ["java","-jar","/app/app.jar"]