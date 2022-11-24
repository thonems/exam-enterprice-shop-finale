FROM amazoncorretto:11-alpine as corretto-jdk
COPY ./target/discovery-serviceservice-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "discovery-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8761