FROM openjdk:21-jdk-slim

WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["sh", "-c", "java -jar -Dopenai.key=$OPENAI_KEY -Dopenai.text-generator.model=$TEXT_GENERATOR_MODEL /app.jar"]
