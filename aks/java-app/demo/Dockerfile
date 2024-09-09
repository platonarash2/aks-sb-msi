# Use the official OpenJDK image from the Docker Hub
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Java application JAR file into the container
COPY target/demo-1.0-SNAPSHOT.jar  /app/demo-1.0-SNAPSHOT.jar

# Set the entry point to run the Java application
ENTRYPOINT ["java", "-jar", "/app/demo-1.0-SNAPSHOT.jar"]
