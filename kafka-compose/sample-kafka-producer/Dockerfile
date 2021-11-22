FROM openjdk:11
COPY target/*.jar kafka-test.jar
ENTRYPOINT ["java","-jar", "kafka-test.jar"]