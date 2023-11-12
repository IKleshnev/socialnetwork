FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/socialnetwork-0.0.1-SNAPSHOT.jar socialnetwork.jar

ENTRYPOINT ["java","-jar","socialnetwork.jar"]
