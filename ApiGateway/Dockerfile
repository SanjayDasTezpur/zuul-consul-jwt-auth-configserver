FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} appApiGateway.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "/appApiGateway.jar"]