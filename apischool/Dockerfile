# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-slim

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Expose the application port
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
