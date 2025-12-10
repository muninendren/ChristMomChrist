# ------------ Stage 1: Build the Application ------------
FROM maven:3.9-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies first (build caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build jar
RUN mvn clean package -DskipTests


# ------------ Stage 2: Run the Application ------------
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy built jar from Stage 1
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
