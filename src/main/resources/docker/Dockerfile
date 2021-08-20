FROM java:8
VOLUME /tmp
ADD project-0.0.1-SNAPSHOT.jar project.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]