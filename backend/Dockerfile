FROM openjdk:17-jdk-slim

WORKDIR /app

# Copier et compiler
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY src ./src

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

EXPOSE 8081

CMD ["java", "-jar", "target/ecompta-international-backend-1.0.0.jar"]
