# Stage 1: Build the application with Maven
FROM maven:3.8.6-alpine AS builder

WORKDIR /app

# Copy the project directory
COPY . .

# Install dependencies
RUN mvn clean install -DskipTests

# Stage 2: Slim runtime container
FROM openjdk:21-slim

# Copy the compiled application
COPY --from=builder /app/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Set working directory
WORKDIR /app

# Entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
