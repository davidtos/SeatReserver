FROM openjdk:13-jdk-alpine
VOLUME /tmp
ADD target/vueblocker-0.0.1-SNAPSHOT.jar target/app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java","-jar","target/app.jar"]