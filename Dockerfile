# Use an official OpenJDK runtime as a parent image
FROM openjdk:11

# Set the working directory in the container
WORKDIR /usr/local/app

# Copy the application's JAR file and configuration files to the container
COPY . .

# Expose the port your application listens on (if applicable)
ENV PORT 80
EXPOSE 80
