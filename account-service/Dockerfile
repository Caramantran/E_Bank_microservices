FROM eclipse-temurin:17-jdk-focal AS BASE
WORKDIR /app
COPY target/account-service-1.0-SNAPSHOT.jar /app/account-service.jar
CMD ["java","-jar","account-service.jar"]