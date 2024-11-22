FROM openjdk:17-jdk

ARG JAR_FILE=build/libs/chatting.jar

WORKDIR /app

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]