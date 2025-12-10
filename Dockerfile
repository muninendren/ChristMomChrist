# ------------ Stage 1: Build the Application ------------
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests


# ------------ Stage 2: Run the Application ------------
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# IMPORTANT: DO NOT EXPOSE A FIXED PORT ON RENDER
# EXPOSE 8080   <-- REMOVE THIS LINE

ENTRYPOINT ["java", "-jar", "app.jar"]
