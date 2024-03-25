# Use official OpenJDK 17 image as base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/legalsight-speech-app-0.0.1-SNAPSHOT.jar /app/legal-speech-app.jar

# Expose the port the application runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "legal-speech-app.jar"]