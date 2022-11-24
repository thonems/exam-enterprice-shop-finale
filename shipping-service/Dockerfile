FROM amazoncorretto:11-alpine as corretto-jdk
COPY ./target/shipping-serviceservice-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "shipping-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8097