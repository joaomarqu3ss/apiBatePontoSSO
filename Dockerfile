FROM openjdk:21

WORKDIR /app

COPY . /app

RUN ./mvnw -B clean package -DskipTests

EXPOSE 8081

CMD ["java", "-jar", "target/apiBatePonto-0.0.1-SNAPSHOT.jar"]