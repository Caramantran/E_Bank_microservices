FROM eclipse-temurin:17-jdk-focal AS BASE
WORKDIR /app
COPY target/transaction-service-1.0-SNAPSHOT.jar /app/transaction-service.jar
CMD ["java","-jar","transaction-service.jar"]