# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM maven:3.6.3-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/java-docker.jar /usr/local/lib/java-docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/java-docker.jar"]
