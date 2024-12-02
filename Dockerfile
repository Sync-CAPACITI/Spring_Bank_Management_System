# Build stage
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Final stage
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/Spring_Bank_Management_System-0.0.1-SNAPSHOT.jar Spring_Bank_Management_System.jar

# Create directory for the H2 database (persistent storage)
RUN mkdir /data

# Expose the port on which Spring Boot app runs
EXPOSE 8000

# Set environment variables for H2 file-based database
ENV SPRING_DATASOURCE_URL=jdbc:h2:file:./data/bank_db;DB_CLOSE_ON_EXIT=FALSE
ENV SPRING_DATASOURCE_DRIVER_CLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=bank
ENV SPRING_DATASOURCE_PASSWORD=SmartBank
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_H2_CONSOLE_ENABLED=true


# Run the Spring Boot application
ENTRYPOINT [ "java", "-jar", "Spring_Bank_Management_System.jar" ]
