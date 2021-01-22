FROM adoptopenjdk/openjdk8:alpine-slim
ADD target/push-counter-1.0-SNAPSHOT-jar-with-dependencies.jar push-counter-1.0-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java", "-jar", "/push-counter-1.0-SNAPSHOT-jar-with-dependencies.jar"]
