FROM java:8
#VOLUME /tmp
ADD project-0.0.1-SNAPSHOT.jar project.jar
RUN bash -c 'touch /project.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/project.jar","--spring.config.location=/dockerPath/springProject/application.yml"]