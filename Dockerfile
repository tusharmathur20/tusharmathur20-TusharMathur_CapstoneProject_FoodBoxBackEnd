FROM openjdk:16
EXPOSE 8081
ADD target/foodbox-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]