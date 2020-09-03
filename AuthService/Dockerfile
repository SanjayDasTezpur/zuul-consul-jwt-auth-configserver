FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} appAuthService.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "/appAuthService.jar"]