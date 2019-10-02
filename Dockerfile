# Jave base image
FROM openjdk:12-alpine

# Maintainer
MAINTAINER Sidney Simmons <sas.simm@gmail.com>

# Make port 8001 open to outside the container
EXPOSE 8001

# The application's jar file
ARG JAR_FILE=build/libs/simple-postgres-0.0.1.jar

# Add the jar to the container
COPY ${JAR_FILE} simple-postgres.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/simple-postgres.jar"]