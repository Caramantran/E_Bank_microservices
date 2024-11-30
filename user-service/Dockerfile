FROM eclipse-temurin:17-jdk-focal AS BASE
WORKDIR /app
COPY target/user-service-1.0-SNAPSHOT.jar /app/user-service.jar
CMD ["java","-jar","user-service.jar"]