FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17
EXPOSE 8080
COPY --from=build /app/target/superheroes.jar superheroes.jar
ENTRYPOINT ["java", "-jar", "/superheroes.jar"]