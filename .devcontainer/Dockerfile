# Use the official Docker-in-Docker image
#FROM docker:20.10.7-dind


#Dockerfile for running docker-cli inside alpine
FROM alpine:3.17
RUN apk add --update docker openrc
RUN rc-update add docker boot

# Install necessary tools
RUN apk add --no-cache \
    openjdk8 \
    maven \
    git

# Set the working directory
WORKDIR /workspace

# Copy the application code
COPY . /workspace

# Expose necessary ports
EXPOSE 8080

# Start Docker daemon
ENTRYPOINT ["dockerd-entrypoint.sh"]
