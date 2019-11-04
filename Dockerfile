FROM openjdk:8
ADD target/spring-batch.jar spring-batch.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "spring-batch.jar"]

