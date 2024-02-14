FROM amazoncorretto:17.0.7-alpine

WORKDIR /app

COPY build/libs/k8s-java-example-1.0-SNAPSHOT.jar /app/k8s-java-example.jar

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "k8s-java-example.jar"]
