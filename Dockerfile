# 1. Basisimage mit JDK 17
FROM eclipse-temurin:17-jdk-alpine

# 2. Temporärer Ordner für Spring Boot
VOLUME /tmp

# 3. JAR aus target kopieren
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# 4. Spring Boot starten
ENTRYPOINT ["java","-jar","/app.jar"]
