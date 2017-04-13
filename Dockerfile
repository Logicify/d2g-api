FROM ubuntu:16.04
RUN apt-get update
RUN apt-get install -y maven openjdk-8-jre
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ADD /d2g-webapi/target/d2g-webapi-1.0-SNAPSHOT.jar webapi.jar
CMD ["java", "-jar", "webapi.jar"]