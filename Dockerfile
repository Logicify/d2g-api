FROM java:8
RUN apt-get install -y maven
RUN mvn clean package
ADD /d2g-webapi/target/d2g-webapi-1.0-SNAPSHOT.jar webapi.jar
CMD ["java", "-jar", "webapi.jar"]