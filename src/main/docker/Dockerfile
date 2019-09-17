FROM openjdk:8-jre-alpine

ENV JAVA_OPTS=""

# Add a spring user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh spring
WORKDIR /home/spring

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown spring:spring entrypoint.sh
USER spring

ADD *.jar app.jar

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080