# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM openjdk:8-jdk-alpine

EXPOSE 8080

ADD target/docker-java.jar app.jar
#VOLUME /tmp
#ADD target/Docker-0.0.1-SNAPSHOT.jar app.jar
#COPY ./target/Docker-0.0.1-SNAPSHOT.jar /usr/app/
#
#WORKDIR /usr/app
#
#RUN sh -c 'touch Docker-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","app.jar"]

