FROM openjdk:11.0

ARG WAR_FILE=./target/*.jar

COPY ${WAR_FILE} webapp.jar

CMD ["java", "-Dspring.profiles.active=test", "-jar", "webapp.jar"]