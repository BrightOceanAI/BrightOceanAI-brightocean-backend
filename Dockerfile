FROM openjdk:21

ARG ENVIRONMENT=docker

ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}

WORKDIR /app

COPY target/BrightOcean-1.0.0-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]