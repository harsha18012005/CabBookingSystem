
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

COPY src ./src

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/cabbooking-0.0.1-SNAPSHOT.jar"]
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://dpg-d3qgr38dl3ps73bur920-a.oregon-postgres.render.com:5432/cabbooking_cglc?sslmode:require
ENV SPRING_DATASOURCE_USERNAME=harsha
ENV SPRING_DATASOURCE_PASSWORD=uLwwhHmgu6YRSoAk1nv5JI8YBPZsNuYc

ENTRYPOINT ["java","-jar","app.jar"]