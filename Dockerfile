FROM eclipse-temurin:17-jdk
ARG WAR_FILE=target/user-event-analytics-0.0.1-SNAPSHOT.war
COPY ${WAR_FILE} application.war
ENTRYPOINT ["java", "-jar", "/application.war"]