FROM amazoncorretto:17
WORKDIR /app
COPY . /app
RUN chmod +x ./mvnw && ./mvnw clean install
CMD ["java", "-jar", "target/backend.jar"]
