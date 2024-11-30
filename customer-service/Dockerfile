FROM eclipse-temurin:17-jdk-focal AS BASE
WORKDIR /app
COPY target/customer-service-1.0-SNAPSHOT.jar /app/customer-service.jar
CMD ["java","-jar","customer-service.jar"]