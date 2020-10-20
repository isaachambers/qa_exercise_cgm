## Base image
FROM adoptopenjdk/openjdk11:alpine-jre

## Copy jar into the container
ADD build/libs/qa_exercise-*-*.jar /opt/cgm/app.jar

# Command to run when container starts
ENTRYPOINT java ${JAVA_OPTS} \
    -XX:+UseContainerSupport \
    -XX:MaxRAMPercentage=50 \
    -Djava.security.egd=file:/dev/./urandom \
    -jar /opt/cgm/app.jar