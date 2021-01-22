FROM adoptopenjdk/openjdk8:alpine-slim
ADD target/red-green-counter-1.0-SNAPSHOT-jar-with-dependencies.jar red-green-counter-1.0-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java", "-jar", "/red-green-counter-1.0-SNAPSHOT-jar-with-dependencies.jar"]
