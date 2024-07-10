FROM openjdk:17-jdk-slim
EXPOSE 8080

ARG JAR_FILE=target/*.jar
RUN echo "JAR_FILE is ${JAR_FILE}"
COPY ${JAR_FILE} /app/app.jar

COPY src/main/resources/application.yml /app/application.yml
COPY .env /app/.env

WORKDIR /app

ENTRYPOINT ["sh", "-c", "java -ea -jar app.jar"]
