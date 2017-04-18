FROM openjdk:8
#ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
COPY . /var/app/
WORKDIR /var/app/
EXPOSE 8080
CMD ["java", "-jar", "d2g-webapi/target/d2g-webapi-1.0-SNAPSHOT.jar"]