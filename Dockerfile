FROM openjdk:8
#ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV db.url=jdbc:postgresql://d2g.ch90nkqvi7vr.us-west-2.rds.amazonaws.com:5432/d2g
ENV db.username=d2g_api
ENV db.password=roseweasel99
COPY . /var/app/
WORKDIR /var/app/
EXPOSE 8080
CMD ["java", "-jar", "d2g-webapi/target/d2g-webapi-1.0-SNAPSHOT.jar"]