FROM ubuntu
#ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk maven
ARG DATABASE_URL
ARG DATABASE_USERNAME
ARG DATABASE_PASSWORD
ENV DATABASE_URL ${DATABASE_URL:-jdbc:postgresql://localhost:5432/d2g}
ENV DATABASE_USERNAME ${DATABASE_USERNAME:-d2g-api}
ENV DATABASE_PASSWORD ${DATABASE_PASSWORD:-roseweasel99}
RUN echo $DATABASE_URL
COPY . /var/app/
WORKDIR /var/app/
RUN mvn clean package -Dliquibase.driver=org.postgresql.Driver -Dliquibase.url=$DATABASE_URL -Dliquibase.username=$DATABASE_USERNAME -Dliquibase.password=$DATABASE_PASSWORD -Dliquibase.changeLogFile=src/main/resources/d2g_database/db.changelog-master.xml
EXPOSE 8080
CMD ["java", "-jar", "d2g-webapi/target/d2g-webapi-1.0-SNAPSHOT.jar"]