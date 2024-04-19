FROM openjdk:21
EXPOSE 8080
ADD target/superheroes.jar superheroes.jar
ENTRYPOINT ["java", "-jar", "/superheroes.jar"]