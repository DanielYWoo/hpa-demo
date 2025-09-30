FROM amazoncorretto:21-alpine-jdk
COPY build/libs/*-SNAPSHOT.jar /main.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "/main.jar"]