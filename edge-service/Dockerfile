FROM eclipse-temurin:17-jdk-focal AS BASE
WORKDIR /app
COPY target/edge-service-1.0-SNAPSHOT.jar /app/edge-service.jar
CMD ["java","-jar","edge-service.jar"]