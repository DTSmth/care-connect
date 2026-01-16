# Step 1: Use a small version of Java
FROM eclipse-temurin:17-jdk-alpine

# Step 2: Copy your compiled JAR file into the container
# Note: You must run 'mvn clean package' first so the 'target' folder has your JAR!
COPY target/*.jar app.jar

# Step 3: Tell the container to run the JAR
ENTRYPOINT ["java","-jar","/app.jar"]