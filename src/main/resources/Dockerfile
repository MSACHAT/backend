FROM openjdk:17
VOLUME /tmp
EXPOSE 8085
COPY ../../../target/backend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]