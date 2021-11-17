FROM openjdk:11
VOLUME /tmp
COPY target/*.jar jborn-web.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/jborn-web.jar"]