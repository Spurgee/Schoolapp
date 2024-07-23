# Use an official Maven image to build the application
FROM maven  AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim
# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar
# Expose the application port
EXPOSE 8081
# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
