FROM amazoncorretto:21
MAINTAINER MoonDance
EXPOSE 8085
ADD target/backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

