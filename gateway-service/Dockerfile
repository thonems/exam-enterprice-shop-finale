FROM amazoncorretto:11-alpine as corretto-jdk
COPY ./target/gateway-serviceservice-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "gateway-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080