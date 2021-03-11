FROM openjdk:8-jdk-alpine
COPY target/*.jar mediscreen-note.jar
ENTRYPOINT ["java","-jar","/mediscreen-note.jar"]