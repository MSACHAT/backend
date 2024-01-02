FROM amazoncorretto:21
MAINTAINER MSACHAT
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]