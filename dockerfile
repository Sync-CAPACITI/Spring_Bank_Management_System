FROM maven:3.8.5-openjdk-17 as build

COPY . .
 
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /target/Spring_Bank_Management_System-0.0.1-SNAPSHOT.jar Spring_Bank_Management_System.jar
 
# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "Spring_Bank_Management_System.jar"]