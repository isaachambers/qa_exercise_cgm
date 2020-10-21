## Base image
FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE
COPY ${JAR_FILE} app.jar