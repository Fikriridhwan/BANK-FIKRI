# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM maven:3.6.0-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} java-docker.jar
ENTRYPOINT ["java","-jar","/java-docker.jar"]
#FROM openjdk:8-jdk-alpine
#
#EXPOSE 8080
#
#COPY --from=build target/java-docker.jar java-docker.jar
##VOLUME /tmp
##ADD target/Docker-0.0.1-SNAPSHOT.jar app.jar
##COPY ./target/Docker-0.0.1-SNAPSHOT.jar /usr/app/
##
##WORKDIR /usr/app
##
##RUN sh -c 'touch Docker-0.0.1-SNAPSHOT.jar'
#ENTRYPOINT ["java","-jar","java-docker.jar"]

